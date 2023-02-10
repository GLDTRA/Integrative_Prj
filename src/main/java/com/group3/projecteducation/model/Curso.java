package com.group3.projecteducation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "tb_cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    private float preco;

    private short avaliacao;

    @ManyToOne
    @JsonIgnoreProperties("curso")
    private Categoria categoria;

    @ManyToMany(mappedBy = "cursosMatriculados")
    @JsonIgnoreProperties("curso")
    Set<Usuario> usuariosMatriculados;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return this.preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public short getAvaliacao() {
        return this.avaliacao;
    }

    public void setAvaliacao(short avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Usuario> getUsuariosMatriculados() {
        return this.usuariosMatriculados;
    }

    public void setUsuariosMatriculados(Set<Usuario> usuariosMatriculados) {
        this.usuariosMatriculados = usuariosMatriculados;
    }

    public Curso() {
    }

    public Curso(Long id, @NotBlank String titulo, @NotBlank String descricao, float preco, short avaliacao, Categoria categoria, Set<Usuario> usuariosMatriculados) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.avaliacao = avaliacao;
        this.categoria = categoria;
        this.usuariosMatriculados = usuariosMatriculados;
    }
}
