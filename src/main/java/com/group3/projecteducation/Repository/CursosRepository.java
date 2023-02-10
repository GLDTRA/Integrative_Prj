package com.group3.projecteducation.repository;

import com.group3.projecteducation.model.Categoria;
import com.group3.projecteducation.model.Curso;
import com.group3.projecteducation.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CursosRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findAllByTituloContainingIgnoreCase(@Param("titulo")String titulo);
    Optional<Curso> findAllByDescricaoContainingIgnoreCase(@Param("descricao")String descricao);
    Optional<Curso> findAllByUsuariosMatriculadosIn(@Param("usuariosMatriculados") Set<Usuario> usuariosMatriculados);
}
