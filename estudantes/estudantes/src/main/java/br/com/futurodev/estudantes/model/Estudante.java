package br.com.futurodev.estudantes.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Estudante {

    @NotNull
    private Integer registro;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    public Estudante() {}

    public Estudante(Integer registro, String nome, String email) {
        this.registro = registro;
        this.nome = nome;
        this.email = email;
    }

    public Integer getRegistro() {
        return registro;
    }

    public void setRegistro(Integer registro) {
        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estudante that)) return false;
        return Objects.equals(registro, that.registro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registro);
    }

    @Override
    public String toString() {
        return "Estudante{" +
                "registro=" + registro +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
