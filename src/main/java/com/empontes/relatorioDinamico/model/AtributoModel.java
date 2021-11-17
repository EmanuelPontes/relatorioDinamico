package com.empontes.relatoriodinamico.model;

import java.io.Serializable;

public class AtributoModel {

    private Integer id;
    private String name;
    private String description;
    private Integer type;
    private Boolean isVisible;
    private Integer priority;     
    private Object value;                                                                 
    
    public Integer getId() {
        return this.id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setId(Integer id) {
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


    public Boolean isIsVisible() {
        return this.isVisible;
    }

    public Boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
