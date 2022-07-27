package com.empontes.relatoriodinamico.repository;

import com.empontes.relatoriodinamico.model.entity.Valor;

import org.springframework.data.repository.CrudRepository;

public interface ValorRepository extends CrudRepository<Valor, Long> {
    
}