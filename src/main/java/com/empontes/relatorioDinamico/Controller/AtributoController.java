package com.empontes.relatoriodinamico.controller;

import java.util.List;

import com.empontes.relatoriodinamico.model.entity.Atributo;
import com.empontes.relatoriodinamico.model.entity.Template;
import com.empontes.relatoriodinamico.model.entity.Valor;
import com.empontes.relatoriodinamico.repository.AtributoRepository;
import com.empontes.relatoriodinamico.repository.TemplateRepository;
import com.empontes.relatoriodinamico.repository.ValorRepository;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/rel/attrbts")
public class AtributoController {
    @Autowired
    private AtributoRepository atributoRepository;

    @Autowired
    private TemplateRepository templateRep;
    
    @Autowired
    private ValorRepository valorRep;

    @GetMapping
    public List findAll() {
       return this.atributoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Atributo findById(@PathVariable Long id) {
       return this.atributoRepository.findById(id).orElse(null);
    }

    @PostMapping("new/{templateId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Atributo addNew(@PathVariable Long templateId, @RequestBody Atributo atributo) {

        Atributo atributoNovo = this.templateRep.findById(templateId).map(template -> {
            long attribId = atributo.getId();

            if (attribId != 0L) {
                Atributo atrib = this.atributoRepository.findById(attribId).orElseThrow(() -> new ResourceClosedException("Atributo " + attribId + " does not exist"));
                template.addAtributo(atrib);
                this.templateRep.save(template);
                return atrib;
            }

            template.addAtributo(atributo);

            return this.atributoRepository.save(atributo);

        }).orElseThrow(() -> new ResourceClosedException("Template " + templateId + " does not exist"));
        
        
        return atributoNovo;


    }


    @PostMapping("add/{id}/value")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String  addNewValue(@PathVariable Long id, @RequestBody String value) {

        valorRep.save(new Valor(value, this.atributoRepository.findById(id).orElse(null)));
        return "New value added successfully";
    }

   
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws NotFoundException {
        this.atributoRepository.findById(id).orElseThrow(NotFoundException::new);
        this.atributoRepository.deleteById(id);

        return "Atributo was sucessfully deleted";
    }

    @DeleteMapping("{templateId}/{attribId}")
    public String deleteFromTemplate(@PathVariable Long templateId, @PathVariable Long attribId) {
        Template template = this.templateRep.findById(templateId).orElseThrow(() -> new ResourceClosedException("Template " + templateId + " does not exist"));
        
        template.removeAtributo(attribId);
        this.templateRep.save(template);

        return "Atributo was sucessfully removed from the template.";
    
    }
}
