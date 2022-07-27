package com.empontes.relatoriodinamico;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.empontes.relatoriodinamico.model.entity.Atributo;
import com.empontes.relatoriodinamico.model.entity.Template;
import com.empontes.relatoriodinamico.model.entity.Valor;
import com.empontes.relatoriodinamico.repository.AtributoRepository;
import com.empontes.relatoriodinamico.repository.TemplateRepository;
import com.empontes.relatoriodinamico.repository.ValorRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


@ExtendWith(SpringExtension.class) 
@SpringBootTest
public class RelatorioEntityTest {

    @Autowired
    private TemplateRepository templateRep;


    @Autowired
    private AtributoRepository attrbRep;

    @Autowired
    private ValorRepository valorRep;


    @Test
    public void populateEntities() {

        Atributo a = new Atributo("A", "Atributo A", 1);
        Atributo b = new Atributo("B", "Atributo B", 1);
        Atributo c = new Atributo("C", "Atributo C", 1);

        Template template = new Template("Relatorio Exemplo");


        template.setAtributos(new HashSet(Arrays.asList(a, b, c)));


        templateRep.save(template);


        valorRep.save(new Valor("1", a));
        valorRep.save(new Valor("2", a));
        valorRep.save(new Valor("3", a));




    }

}