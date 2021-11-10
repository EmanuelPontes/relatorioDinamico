package com.empontes.relatoriodinamico.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Relatorio implements Serializable{
    static final long serialVersionUID = 1L;
    private  List<AtributoModel> atributos =  new ArrayList<>();
    private Map<String, Object> parameters = new HashMap<>();

    Relatorio(List<AtributoModel> atributos) {
        this.atributos = new ArrayList<>(atributos);
        parameters.put("AtributosDataSource", this.atributos);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
