package com.sebastiao.reciclaville.dto;

public class MaterialResponse {

    private Long id;
    private String nome;
    private Double percentualCompensacao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPercentualCompensacao() { return percentualCompensacao; }
    public void setPercentualCompensacao(Double percentualCompensacao) { this.percentualCompensacao = percentualCompensacao; }
}
