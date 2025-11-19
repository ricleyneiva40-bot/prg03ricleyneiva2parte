/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 *
 * @author ricle
 */
public interface CursoIController {
    // metodos abstratos para serem implementados
    public abstract void save(Curso curso);
    public abstract void update(Curso curso);
    public abstract void delete(Curso curso);
    public abstract List<Curso> findAll();
    public abstract Curso findById(Long id);
    public abstract List<Curso> findByCodigo(String codigo);
}
