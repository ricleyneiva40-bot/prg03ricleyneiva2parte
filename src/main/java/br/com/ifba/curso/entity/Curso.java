/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author ricle
 */
@Entity
@Table(name = "Cursos")
public class Curso extends PersistenceEntity implements Serializable{
    @Column(name = "codigo", nullable = false, unique = true)
    private String codigoCurso;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "Ativo") 
    private boolean ativo;

    public Curso() {
    }

    public Curso(String codigo, String nome, boolean ativo) {
        this.codigoCurso = codigo;
        this.nome = nome;
        this.ativo = ativo;
    }

    public String getCodigo() {
        return codigoCurso;
    }

    public void setCodigo(String codigo) {
        this.codigoCurso = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
