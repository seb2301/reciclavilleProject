package com.sebastiao.reciclaville.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Usuario() {}

    public Usuario(String nome, String username, String senha, Perfil perfil, Cliente cliente) {
        this.nome = nome;
        this.username = username;
        this.senha = senha;
        this.perfil = perfil;
        this.cliente = cliente;
    }



    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
