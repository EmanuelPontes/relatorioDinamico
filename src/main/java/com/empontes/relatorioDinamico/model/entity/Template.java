package com.empontes.relatoriodinamico.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//owner of relationship
@Entity
@Table(name = "template")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
    @JoinTable(
        name = "template_atributo",
        joinColumns = { @JoinColumn(name = "template_id") },
        inverseJoinColumns = { @JoinColumn(name = "attrb_id") }
    )
    private Set<Atributo> atributos = new HashSet<>();



    public Template() {
    }

    public Template(String name) {
    
        this.name = name;
    }



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


    public Set<Atributo> getAtributos() {
        return this.atributos;
    }

    public void setAtributos(Set<Atributo> atributos) {
        this.atributos = atributos;
    }
    

    public void addAtributo(Atributo atributo) {
        this.atributos.add(atributo);
        atributo.getTemplate().add(this);
    }

    public void removeAtributo(long attribId) {
        Atributo atributo = this.atributos.stream().filter(a -> a.getId() == attribId).findFirst().orElse(null);
        if (atributo != null) {
          this.atributos.remove(atributo);
          atributo.getTemplate().remove(this);
        }
    }



}
