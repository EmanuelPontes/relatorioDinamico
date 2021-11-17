package com.empontes.relatoriodinamico.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Relatorio implements Serializable{
    static final long serialVersionUID = 1L;
    private  List<AtributoModel> atributos =  new ArrayList<>();

    

    
    private Map<String, Object> parameters = new HashMap<>();

    public Relatorio(List<AtributoModel> atributos) {
        this.atributos = new ArrayList<>(atributos);
        parameters.put("AtributosDataSource", new JRBeanCollectionDataSource(this.atributos));
        parameters.put("title","Teste relatorio dinamico");
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public List<AtributoModel> getAtributos() {
        return this.atributos;
    }


}
