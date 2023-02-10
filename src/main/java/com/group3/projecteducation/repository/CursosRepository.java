package com.group3.projecteducation.repository;

import com.group3.projecteducation.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CursosRepository extends JpaRepository<Curso, Long> {
    List<Curso> findAllByTituloContainingIgnoreCase(@Param("titulo")String titulo);
    List<Curso> findAllByDescricaoContainingIgnoreCase(@Param("descricao")String descricao);
}
