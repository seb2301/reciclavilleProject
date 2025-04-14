package br.com.futurodev.estudantes.controller;

import br.com.futurodev.estudantes.model.Estudante;
import br.com.futurodev.estudantes.repository.Database;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {

    @PostMapping
    public ResponseEntity<Estudante> cadastrar(@RequestBody @Valid Estudante estudante) {
        Database.adicionar(estudante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudante);
    }

    @GetMapping
    public ResponseEntity<List<Estudante>> listarTodos() {
        List<Estudante> lista = Database.listarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{registro}")
    public ResponseEntity<Estudante> buscarPorRegistro(@PathVariable Integer registro) {
        return Database.buscarPorRegistro(registro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{registro}")
    public ResponseEntity<Void> deletar(@PathVariable Integer registro) {
        boolean removido = Database.remover(registro);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
