package com.sebastiao.reciclaville.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @NotBlank
    @Column(name = "atividade_economica", nullable = false)
    private String atividadeEconomica;

    @NotBlank
    @Column(nullable = false)
    private String responsavel;

    public Cliente() {}

    public Cliente(String nome, String cnpj, String atividadeEconomica, String responsavel) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.atividadeEconomica = atividadeEconomica;
        this.responsavel = responsavel;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getAtividadeEconomica() { return atividadeEconomica; }
    public void setAtividadeEconomica(String atividadeEconomica) { this.atividadeEconomica = atividadeEconomica; }
    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
}
