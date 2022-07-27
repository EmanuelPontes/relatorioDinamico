package com.empontes.relatoriodinamico.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "valor")
public class Valor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attrb_id", nullable = false)
    private Atributo atributo;



    public Valor() {
    }


    public Valor(String value) {
        this.value = value;
    }

    public Valor(String value, Atributo atributo) {
        this.value = value;
        this.atributo = atributo;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getAtributoId() {
        return this.atributo.getId();
    }

}
