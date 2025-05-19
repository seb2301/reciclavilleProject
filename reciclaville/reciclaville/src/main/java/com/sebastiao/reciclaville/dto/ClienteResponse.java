package com.sebastiao.reciclaville.dto;

public class ClienteResponse {

    private Long id;
    private String nome;
    private String cnpj;
    private String atividadeEconomica;
    private String responsavel;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getAtividadeEconomica() { return atividadeEconomica; }
    public void setAtividadeEconomica(String atividadeEconomica) { this.atividadeEconomica = atividadeEconomica; }
    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
}
