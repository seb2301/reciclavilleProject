package com.sebastiao.reciclaville.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemDeclaracaoRequest {

    @NotNull
    private Long materialId;

    @NotNull @Positive
    private Double toneladasDeclaradas;

    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }
    public Double getToneladasDeclaradas() { return toneladasDeclaradas; }
    public void setToneladasDeclaradas(Double toneladasDeclaradas) { this.toneladasDeclaradas = toneladasDeclaradas; }
}
