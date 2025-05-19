package com.sebastiao.reciclaville.dto;


public class DashboardItem {
    private String materialNome;
    private Double totalCompensacao;

    public DashboardItem(String materialNome, Double totalCompensacao) {
        this.materialNome = materialNome;
        this.totalCompensacao = totalCompensacao;
    }

    public String getMaterialNome() {
        return materialNome;
    }

    public void setMaterialNome(String materialNome) {
        this.materialNome = materialNome;
    }

    public Double getTotalCompensacao() {
        return totalCompensacao;
    }

    public void setTotalCompensacao(Double totalCompensacao) {
        this.totalCompensacao = totalCompensacao;
    }
}
