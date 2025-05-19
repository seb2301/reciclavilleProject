package com.sebastiao.reciclaville.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "itens_declaracao")
public class ItemDeclaracao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "declaracao_id", nullable = false)
    private Declaracao declaracao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @NotNull
    @Column(name = "percentual_compensacao", nullable = false)
    private Double percentualCompensacao;

    @NotNull
    @Column(name = "toneladas_declaradas", nullable = false)
    private Double toneladasDeclaradas;

    @NotNull
    @Column(name = "toneladas_compensacao", nullable = false)
    private Double toneladasCompensacao;

    public ItemDeclaracao() {}

    public ItemDeclaracao(Declaracao declaracao, Material material,
                          Double percentualCompensacao,
                          Double toneladasDeclaradas,
                          Double toneladasCompensacao) {
        this.declaracao = declaracao;
        this.material = material;
        this.percentualCompensacao = percentualCompensacao;
        this.toneladasDeclaradas = toneladasDeclaradas;
        this.toneladasCompensacao = toneladasCompensacao;
    }

    public Long getId() { return id; }
    public Declaracao getDeclaracao() { return declaracao; }
    public void setDeclaracao(Declaracao declaracao) { this.declaracao = declaracao; }
    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }
    public Double getPercentualCompensacao() { return percentualCompensacao; }
    public void setPercentualCompensacao(Double percentualCompensacao) { this.percentualCompensacao = percentualCompensacao; }
    public Double getToneladasDeclaradas() { return toneladasDeclaradas; }
    public void setToneladasDeclaradas(Double toneladasDeclaradas) { this.toneladasDeclaradas = toneladasDeclaradas; }
    public Double getToneladasCompensacao() { return toneladasCompensacao; }
    public void setToneladasCompensacao(Double toneladasCompensacao) { this.toneladasCompensacao = toneladasCompensacao; }
}
