package com.empontes.relatoriodinamico.repository;

import java.util.List;
import com.empontes.relatoriodinamico.model.entity.Atributo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AtributoRepository extends JpaRepository<Atributo,Long>{
    List<Atributo> findAll();
}
