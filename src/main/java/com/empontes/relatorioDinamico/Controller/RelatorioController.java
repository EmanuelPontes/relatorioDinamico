package com.empontes.relatoriodinamico.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.empontes.relatoriodinamico.model.AtributoModel;
import com.empontes.relatoriodinamico.model.RelatorioModel;
import com.empontes.relatoriodinamico.model.entity.Atributo;
import com.empontes.relatoriodinamico.model.entity.Template;
import com.empontes.relatoriodinamico.repository.AtributoRepository;
import com.empontes.relatoriodinamico.repository.TemplateRepository;
import com.empontes.relatoriodinamico.services.RelatorioService;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/rel")
public class RelatorioController {
    
    @Autowired
    private RelatorioService relatorioService;


    @Autowired
    private TemplateRepository templateRep;

    @Autowired
    private AtributoRepository atributoRep;



    @GetMapping
    public List getAll() { 

        return this.templateRep.findAll();
    }

    @GetMapping("/{id}")
    public Template getById(@PathVariable Long id) { 

        return this.templateRep.findById(id).orElseThrow(() -> new ResourceClosedException("Template " + id + " does not exist"));
    }


    @PostMapping("/create")
    public String create(@RequestParam String name, @RequestBody List<Atributo> atributos) {

        
        Template template = new Template(name);


        
        atributos.forEach(a -> {

            Atributo _a = atributoRep.findById(a.getId()).orElse(null);
            if (_a != null) { 
                template.addAtributo(_a);
            }
            
        });

        
        templateRep.save(template);

        return "Template created successfully";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody Template template) throws ResourceClosedException {

        Template _template = this.templateRep.findById(id).orElseThrow(() -> new ResourceClosedException("Not found Tutorial with id = " + id));

        _template.setName(template.getName());
        
        this.templateRep.save(_template);

        return "Template updated successfully";

    }


    @GetMapping("/gerar/{id}")
    public void gerar(HttpServletResponse response, @PathVariable Long id) throws IOException {
        

        Template template = this.templateRep.findById(id).orElseThrow(() -> new ResourceClosedException("Template " + id + " does not exist"));
        RelatorioModel relatorio = new RelatorioModel(template);
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio.pdf");
        this.relatorioService.generateReport(relatorio, response.getOutputStream());
    }



    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        this.templateRep.deleteById(id);

        return "Template deleted successfully";

    }

}
