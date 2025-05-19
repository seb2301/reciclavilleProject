package com.sebastiao.reciclaville.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "declaracoes")
public class Declaracao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull
    @Column(name = "data_declaracao", nullable = false)
    private LocalDate dataDeclaracao;

    @NotNull
    @Column(name = "data_inicio_periodo", nullable = false)
    private LocalDate dataInicioPeriodo;

    @NotNull
    @Column(name = "data_final_periodo", nullable = false)
    private LocalDate dataFinalPeriodo;

    @NotNull
    @Column(name = "total_materiais", nullable = false)
    private Double totalMateriais;

    @NotNull
    @Column(name = "total_compensacao", nullable = false)
    private Double totalCompensacao;

    @OneToMany(mappedBy = "declaracao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDeclaracao> itens = new ArrayList<>();

    public Declaracao() {}

    public Declaracao(Cliente cliente, LocalDate dataDeclaracao,
                      LocalDate dataInicioPeriodo, LocalDate dataFinalPeriodo,
                      Double totalMateriais, Double totalCompensacao) {
        this.cliente = cliente;
        this.dataDeclaracao = dataDeclaracao;
        this.dataInicioPeriodo = dataInicioPeriodo;
        this.dataFinalPeriodo = dataFinalPeriodo;
        this.totalMateriais = totalMateriais;
        this.totalCompensacao = totalCompensacao;
    }

    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
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
    public List<ItemDeclaracao> getItens() { return itens; }
    public void setItens(List<ItemDeclaracao> itens) { this.itens = itens; }
}
