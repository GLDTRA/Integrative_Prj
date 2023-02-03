package com.group3.projecteducation.Repository;

import com.group3.projecteducation.model.Categoria;
import com.group3.projecteducation.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursosRepository extends JpaRepository<Curso, Long> {
    public Optional<Curso> findAllByTituloContainingIgnoreCase(@Param("titulo")String titulo);
    public Optional<Curso> findAllByDescricaoContainingIgnoreCase(@Param("titulo")String descricao);

}
