package com.sebastiao.reciclaville.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "materiais")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @NotNull
    @Column(name = "percentual_compensacao", nullable = false)
    private Double percentualCompensacao;

    public Material() {}

    public Material(String nome, Double percentualCompensacao) {
        this.nome = nome;
        this.percentualCompensacao = percentualCompensacao;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPercentualCompensacao() { return percentualCompensacao; }
    public void setPercentualCompensacao(Double percentualCompensacao) { this.percentualCompensacao = percentualCompensacao; }
}
