package com.sebastiao.reciclaville.dto;

import jakarta.validation.constraints.NotBlank;


public class ClienteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String atividadeEconomica;

    @NotBlank
    private String responsavel;

    public ClienteRequest() {
    }


    public ClienteRequest(String nome, String cnpj, String atividadeEconomica, String responsavel) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.atividadeEconomica = atividadeEconomica;
        this.responsavel = responsavel;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAtividadeEconomica() {
        return atividadeEconomica;
    }

    public void setAtividadeEconomica(String atividadeEconomica) {
        this.atividadeEconomica = atividadeEconomica;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
