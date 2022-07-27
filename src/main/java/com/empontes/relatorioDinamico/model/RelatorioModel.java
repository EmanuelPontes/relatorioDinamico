package com.empontes.relatoriodinamico.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.empontes.relatoriodinamico.model.entity.Atributo;
import com.empontes.relatoriodinamico.model.entity.Template;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioModel implements Serializable{
    static final long serialVersionUID = 1L;
    private  List<Atributo> atributos =  new ArrayList<>();

    

    
    private Map<String, Object> parameters = new HashMap<>();

    public RelatorioModel(Template template) {
        this.atributos = new ArrayList<>(template.getAtributos());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(this.atributos);
        parameters.put("AtributosDataSource", dataSource);
        parameters.put("title", template.getName());
        parameters.put("ReportPath","C:/Users/Emanuel Pontes SIS/OneDrive/Documentos/github/RelatorioDinamico/relatorioDinamico/src/main/resources/reports/");
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public List<Atributo> getAtributos() {
        return this.atributos;
    }


}
