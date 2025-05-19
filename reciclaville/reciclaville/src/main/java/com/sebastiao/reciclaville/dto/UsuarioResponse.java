package com.sebastiao.reciclaville.dto;

import com.sebastiao.reciclaville.model.Perfil;

public class UsuarioResponse {

    private Long id;
    private String nome;
    private String username;
    private Perfil perfil;
    private Long clienteId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}
