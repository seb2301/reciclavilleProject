package com.sebastiao.reciclaville.service;

import com.sebastiao.reciclaville.dto.UsuarioRequest;
import com.sebastiao.reciclaville.dto.UsuarioResponse;
import com.sebastiao.reciclaville.exception.ResourceNotFoundException;
import com.sebastiao.reciclaville.model.Cliente;
import com.sebastiao.reciclaville.model.Usuario;
import com.sebastiao.reciclaville.repository.ClienteRepository;
import com.sebastiao.reciclaville.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository userRepo;
    private final ClienteRepository clienteRepo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository userRepo,
                          ClienteRepository clienteRepo,
                          PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.clienteRepo = clienteRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponse> listarTodos() {
        return userRepo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario u = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
        return toResponse(u);
    }

    public UsuarioResponse criar(UsuarioRequest req) {
        Cliente cliente = null;
        if (req.getPerfil().toString().equals("USER")) {
            cliente = clienteRepo.findById(req.getClienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + req.getClienteId()));
        }
        Usuario u = new Usuario();
        u.setNome(req.getNome());
        u.setUsername(req.getUsername());
        // criptografar a senha (verificar)
        u.setSenha(passwordEncoder.encode(req.getSenha()));
        u.setPerfil(req.getPerfil());
        u.setCliente(cliente);
        Usuario salvo = userRepo.save(u);
        return toResponse(salvo);
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest req) {
        Usuario u = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));

        Cliente cliente = null;
        if (req.getPerfil().toString().equals("USER")) {
            cliente = clienteRepo.findById(req.getClienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + req.getClienteId()));
        }

        u.setNome(req.getNome());
        u.setUsername(req.getUsername());
        if (req.getSenha() != null && !req.getSenha().isBlank()) {
            u.setSenha(passwordEncoder.encode(req.getSenha()));
        }
        u.setPerfil(req.getPerfil());
        u.setCliente(cliente);
        return toResponse(userRepo.save(u));
    }

    public void excluir(Long id) {
        if (!userRepo.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado: " + id);
        }
        userRepo.deleteById(id);
    }

    private UsuarioResponse toResponse(Usuario u) {
        UsuarioResponse resp = new UsuarioResponse();
        resp.setId(u.getId());
        resp.setNome(u.getNome());
        resp.setUsername(u.getUsername());
        resp.setPerfil(u.getPerfil());
        resp.setClienteId(u.getCliente() != null ? u.getCliente().getId() : null);
        return resp;
    }
}
