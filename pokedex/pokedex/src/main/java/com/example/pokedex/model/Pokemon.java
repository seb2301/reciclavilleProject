package com.example.pokedex.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pokemon {

    @Id
    private String numero;
    private String nome;
    private String descricao;
    private String imagem;
    private String tipo;
    private String categoria;
    private String areaHabita;
    private double altura;
    private double peso;


    public Pokemon(String numero, String nome, String descricao, String imagem, String tipo, String categoria, String areaHabita, double altura, double peso) {
        this.numero = numero;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.tipo = tipo;
        this.categoria = categoria;
        this.areaHabita = areaHabita;
        this.altura = altura;
        this.peso = peso;
    }

}
