package com.empontes.relatoriodinamico.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


// target of relationship 
@Entity
@Table(name = "atributo")
public class Atributo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Integer type;




    public Atributo() {
    }

    public Atributo(String namme, String description, Integer type) {
        
        this.name = namme;
        this.description = description;
        this.type = type;
    }


    @OneToMany(mappedBy = "atributo", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<Valor> values;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    },
    mappedBy = "atributos")
    @JsonIgnore
    private List<Template> template;

    // Criar uma rela��o de 1 pra muitos para  armazenar a lista de valores de cada atributo
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public List<Valor> getValues() {
        return this.values;
    }

    public void setValues(List<Valor> values) {
        this.values = values;
    }

  

    public List<Template> getTemplate() {
        return this.template;
    }

    public void setTemplate(List<Template> template) {
        this.template = template;
    }



}
