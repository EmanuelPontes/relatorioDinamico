package com.empontes.relatorioDinamico.Controller;

import java.util.List;

import com.empontes.relatorioDinamico.Model.Atributo;
import com.empontes.relatorioDinamico.Repository.AtributoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rel/attrbts")
public class AtributoController {
    @Autowired
    private AtributoRepository atributoRepository;

    @GetMapping
    public List findAll() {
       return this.atributoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Atributo addNew(@RequestBody Atributo atributo) {
        return this.atributoRepository.save(atributo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        this.atributoRepository.findById(id).orElseThrow(NotFoundException::new);
        this.atributoRepository.deleteById(id);
    }
}
