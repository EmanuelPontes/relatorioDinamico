package com.empontes.relatorioDinamico.Repository;

import java.util.List;

import com.empontes.relatorioDinamico.Model.Atributo;

import org.springframework.data.repository.CrudRepository;

public interface AtributoRepository extends CrudRepository<Atributo,Long>{
    List<Atributo> findAll();
}
