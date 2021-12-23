package com.empontes.relatoriodinamico.model;

import java.io.Serializable;
import java.util.List;

public class AtributoModel {

    private Integer id;
    private String name;
    private String description;
    private Integer type;
    private Boolean isVisible;
    private Integer priority;     
    private List<Object> values;                                                                 
    
    public Integer getId() {
        return this.id;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
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
