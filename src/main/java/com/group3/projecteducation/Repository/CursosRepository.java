package com.group3.projecteducation.Repository;

import com.group3.projecteducation.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosRepository extends JpaRepository<Curso, Long> {

}
