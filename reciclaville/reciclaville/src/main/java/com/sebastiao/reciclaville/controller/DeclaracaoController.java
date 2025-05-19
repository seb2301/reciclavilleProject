package com.sebastiao.reciclaville.controller;

import com.sebastiao.reciclaville.dto.DeclaracaoRequest;
import com.sebastiao.reciclaville.dto.DeclaracaoResponse;
import com.sebastiao.reciclaville.service.DeclaracaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/declaracoes")
public class DeclaracaoController {

    private final DeclaracaoService service;

    public DeclaracaoController(DeclaracaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeclaracaoResponse> criar(@RequestBody @Valid DeclaracaoRequest req) {
        DeclaracaoResponse criado = service.criar(req);
        URI uri = URI.create("/declaracoes/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    @GetMapping
    public List<DeclaracaoResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeclaracaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
