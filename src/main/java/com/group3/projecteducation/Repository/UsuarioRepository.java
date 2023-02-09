package com.group3.projecteducation.Repository;

import com.group3.projecteducation.TipoUsuario;
import com.group3.projecteducation.model.Curso;
import com.group3.projecteducation.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(@Param("email") String email);
    Optional<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
    Optional<Usuario> findAllByTipoUsuario(@Param("tipoUsuario") TipoUsuario tipoUsuario);
}
