package com.group3.projecteducation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group3.projecteducation.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;


@Entity
@Table(name = "tb_usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String usuario;

    @NotBlank
    private String senha;

    @NotBlank
    private String tipoUsuario;

   @ManyToMany
    @JsonIgnoreProperties("usuario")
    @JoinTable(
            name = "cursos_matriculados",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursosMatriculados;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Set<Curso> getCursosMatriculados() {
        return this.cursosMatriculados;
    }

    public void setCursosMatriculados(Set<Curso> cursosMatriculados) {
        this.cursosMatriculados = cursosMatriculados;
    }

    public Usuario() {
    }

    public Usuario(Long id, @NotBlank String nome, @NotBlank String usuario, @NotBlank String senha, @NotBlank String tipoUsuario, Set<Curso> cursosMatriculados) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.cursosMatriculados = cursosMatriculados;
    }
}
