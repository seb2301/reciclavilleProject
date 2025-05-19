package com.sebastiao.reciclaville.dto;

import java.time.LocalDate;
import java.util.List;

public class DeclaracaoResponse {

    private Long id;
    private Long clienteId;
    private LocalDate dataDeclaracao;
    private LocalDate dataInicioPeriodo;
    private LocalDate dataFinalPeriodo;
    private Double totalMateriais;
    private Double totalCompensacao;
    private List<ItemDeclaracaoResponse> itens;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public LocalDate getDataDeclaracao() { return dataDeclaracao; }
    public void setDataDeclaracao(LocalDate dataDeclaracao) { this.dataDeclaracao = dataDeclaracao; }
    public LocalDate getDataInicioPeriodo() { return dataInicioPeriodo; }
    public void setDataInicioPeriodo(LocalDate dataInicioPeriodo) { this.dataInicioPeriodo = dataInicioPeriodo; }
    public LocalDate getDataFinalPeriodo() { return dataFinalPeriodo; }
    public void setDataFinalPeriodo(LocalDate dataFinalPeriodo) { this.dataFinalPeriodo = dataFinalPeriodo; }
    public Double getTotalMateriais() { return totalMateriais; }
    public void setTotalMateriais(Double totalMateriais) { this.totalMateriais = totalMateriais; }
    public Double getTotalCompensacao() { return totalCompensacao; }
    public void setTotalCompensacao(Double totalCompensacao) { this.totalCompensacao = totalCompensacao; }
    public List<ItemDeclaracaoResponse> getItens() { return itens; }
    public void setItens(List<ItemDeclaracaoResponse> itens) { this.itens = itens; }
}
