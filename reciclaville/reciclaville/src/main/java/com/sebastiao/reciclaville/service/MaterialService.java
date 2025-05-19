package com.sebastiao.reciclaville.service;

import com.sebastiao.reciclaville.dto.MaterialRequest;
import com.sebastiao.reciclaville.dto.MaterialResponse;
import com.sebastiao.reciclaville.exception.ResourceNotFoundException;
import com.sebastiao.reciclaville.model.Material;
import com.sebastiao.reciclaville.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository repo;

    public MaterialService(MaterialRepository repo) {
        this.repo = repo;
    }

    public List<MaterialResponse> listarTodos() {
        return repo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public MaterialResponse buscarPorId(Long id) {
        Material m = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material não encontrado: " + id));
        return toResponse(m);
    }

    public MaterialResponse criar(MaterialRequest req) {
        Material m = new Material(req.getNome(), req.getPercentualCompensacao());
        Material salvo = repo.save(m);
        return toResponse(salvo);
    }

    public MaterialResponse atualizar(Long id, MaterialRequest req) {
        Material m = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material não encontrado: " + id));
        m.setNome(req.getNome());
        m.setPercentualCompensacao(req.getPercentualCompensacao());
        return toResponse(repo.save(m));
    }

    public void excluir(Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Material não encontrado: " + id);
        repo.deleteById(id);
    }

    private MaterialResponse toResponse(Material m) {
        MaterialResponse resp = new MaterialResponse();
        resp.setId(m.getId());
        resp.setNome(m.getNome());
        resp.setPercentualCompensacao(m.getPercentualCompensacao());
        return resp;
    }
}
