package com.group3.projecteducation.repository;

import com.group3.projecteducation.model.Curso;
import com.group3.projecteducation.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(@Param("usuario") String usuario);
    List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
    List<Usuario> findAllByTipoUsuario(@Param("tipoUsuario") String tipoUsuario);
    List<Usuario> findAllByCurso (@Param("curso") Optional<Curso> curso);
}
