package com.group3.projecteducation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 0, max = 1000)
    private String descricao;

    @NotBlank
    private String titulo;

    @NotBlank
    private String area;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categoria")
    private Set<Curso> curso;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Set<Curso> getCurso() {
        return this.curso;
    }

    public void setCurso(Set<Curso> curso) {
        this.curso = curso;
    }

    public Categoria() {
    }

    public Categoria(Long id, @Size(min = 0, max = 1000) String descricao, @NotBlank String titulo, @NotBlank String area, Set<Curso> curso) {
        this.id = id;
        this.descricao = descricao;
        this.titulo = titulo;
        this.area = area;
        this.curso = curso;
    }
}
