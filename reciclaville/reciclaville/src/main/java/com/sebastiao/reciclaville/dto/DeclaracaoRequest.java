package com.sebastiao.reciclaville.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;

public class DeclaracaoRequest {

    @NotNull
    private Long clienteId;

    @NotNull @PastOrPresent
    private LocalDate dataInicioPeriodo;

    @NotNull @PastOrPresent
    private LocalDate dataFinalPeriodo;

    @NotNull
    private List<ItemDeclaracaoRequest> itens;

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public LocalDate getDataInicioPeriodo() { return dataInicioPeriodo; }
    public void setDataInicioPeriodo(LocalDate dataInicioPeriodo) { this.dataInicioPeriodo = dataInicioPeriodo; }
    public LocalDate getDataFinalPeriodo() { return dataFinalPeriodo; }
    public void setDataFinalPeriodo(LocalDate dataFinalPeriodo) { this.dataFinalPeriodo = dataFinalPeriodo; }
    public List<ItemDeclaracaoRequest> getItens() { return itens; }
    public void setItens(List<ItemDeclaracaoRequest> itens) { this.itens = itens; }
}
