package com.empontes.relatorioDinamico.repository;

import java.util.List;
import com.empontes.relatorioDinamico.model.Atributo;
import org.springframework.data.repository.CrudRepository;

public interface AtributoRepository extends CrudRepository<Atributo,Long>{
    List<Atributo> findAll();
}
