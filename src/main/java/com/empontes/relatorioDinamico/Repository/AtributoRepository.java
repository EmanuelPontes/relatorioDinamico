package com.empontes.relatoriodinamico.repository;

import java.util.List;
import com.empontes.relatoriodinamico.model.entity.Atributo;
import org.springframework.data.repository.CrudRepository;

public interface AtributoRepository extends CrudRepository<Atributo,Long>{
    List<Atributo> findAll();
}
