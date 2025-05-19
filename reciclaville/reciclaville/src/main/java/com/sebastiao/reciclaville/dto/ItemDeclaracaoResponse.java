package com.sebastiao.reciclaville.dto;

public class ItemDeclaracaoResponse {

    private Long id;
    private Long materialId;
    private String materialNome;
    private Double percentualCompensacao;
    private Double toneladasDeclaradas;
    private Double toneladasCompensacao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }
    public String getMaterialNome() { return materialNome; }
    public void setMaterialNome(String materialNome) { this.materialNome = materialNome; }
    public Double getPercentualCompensacao() { return percentualCompensacao; }
    public void setPercentualCompensacao(Double percentualCompensacao) { this.percentualCompensacao = percentualCompensacao; }
    public Double getToneladasDeclaradas() { return toneladasDeclaradas; }
    public void setToneladasDeclaradas(Double toneladasDeclaradas) { this.toneladasDeclaradas = toneladasDeclaradas; }
    public Double getToneladasCompensacao() { return toneladasCompensacao; }
    public void setToneladasCompensacao(Double toneladasCompensacao) { this.toneladasCompensacao = toneladasCompensacao; }
}
