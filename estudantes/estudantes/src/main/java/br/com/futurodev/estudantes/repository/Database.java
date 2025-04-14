package br.com.futurodev.estudantes.repository;

import br.com.futurodev.estudantes.model.Estudante;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {
    private static final List<Estudante> estudantes = new ArrayList<>();

    public static void adicionar(Estudante estudante) {
        estudantes.add(estudante);
    }

    public static boolean remover(Integer registro) {
        return estudantes.removeIf(e -> e.getRegistro().equals(registro));
    }

    public static List<Estudante> listarTodos() {
        return estudantes;
    }

    public static Optional<Estudante> buscarPorRegistro(Integer registro) {
        return estudantes.stream()
                .filter(e -> e.getRegistro().equals(registro))
                .findFirst();
    }
}
