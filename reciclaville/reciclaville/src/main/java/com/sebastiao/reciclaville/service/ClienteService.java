package com.sebastiao.reciclaville.service;

import com.sebastiao.reciclaville.dto.ClienteRequest;
import com.sebastiao.reciclaville.dto.ClienteResponse;
import com.sebastiao.reciclaville.exception.ResourceNotFoundException;
import com.sebastiao.reciclaville.model.Cliente;
import com.sebastiao.reciclaville.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<ClienteResponse> listarTodos() {
        return repo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente c = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
        return toResponse(c);
    }

    public ClienteResponse criar(ClienteRequest req) {
        Cliente c = new Cliente(req.getNome(), req.getCnpj(), req.getAtividadeEconomica(), req.getResponsavel());
        Cliente salvo = repo.save(c);
        return toResponse(salvo);
    }

    public ClienteResponse atualizar(Long id, ClienteRequest req) {
        Cliente c = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
        c.setNome(req.getNome());
        c.setCnpj(req.getCnpj());
        c.setAtividadeEconomica(req.getAtividadeEconomica());
        c.setResponsavel(req.getResponsavel());
        return toResponse(repo.save(c));
    }

    public void excluir(Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Cliente não encontrado: " + id);
        repo.deleteById(id);
    }

    private ClienteResponse toResponse(Cliente c) {
        ClienteResponse resp = new ClienteResponse();
        resp.setId(c.getId());
        resp.setNome(c.getNome());
        resp.setCnpj(c.getCnpj());
        resp.setAtividadeEconomica(c.getAtividadeEconomica());
        resp.setResponsavel(c.getResponsavel());
        return resp;
    }
}
