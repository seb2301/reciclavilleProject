package com.sebastiao.reciclaville.dto;

public class MaterialRequest {
    private String nome;
    private Double percentualCompensacao;

    public MaterialRequest() {}

    public MaterialRequest(String nome, Double percentualCompensacao) {
        this.nome = nome;
        this.percentualCompensacao = percentualCompensacao;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getPercentualCompensacao() {
        return percentualCompensacao;
    }
    public void setPercentualCompensacao(Double percentualCompensacao) {
        this.percentualCompensacao = percentualCompensacao;
    }
}
