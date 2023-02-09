package com.group3.projecteducation.repository;

import com.group3.projecteducation.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Long> {
<<<<<<< Updated upstream:src/main/java/com/group3/projecteducation/Repository/CategoriasRepository.java
    public Optional<Categoria> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    public Optional<Categoria> findAllByAreaContainingIgnoreCase(@Param("area") String area);
=======


    public List<Categoria> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    public List<Categoria> findAllByAreaContainingIgnoreCase(@Param("area") String area);
>>>>>>> Stashed changes:src/main/java/com/group3/projecteducation/repository/CategoriasRepository.java


}
