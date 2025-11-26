/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ricle
 */
//Controller ja com Spring 
@Controller
public class CursoController implements CursoIController{

    //Aqui onde ficam as regras de negocio, sem acoplamento
    @Autowired
    private CursoIService cursoService;
    
    @Override
    public void save(Curso curso) {
        cursoService.save(curso);
    }

    @Override
    public void update(Curso curso) {
        cursoService.update(curso);
    }

    @Override
    public void delete(Curso curso) {
        cursoService.delete(curso);
    }

    @Override
    public List<Curso> findAll() {
        return cursoService.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return cursoService.findById(id);
    }

    @Override
    public List<Curso> findByCodigo(String codigo) {
        return cursoService.findByCodigo(codigo);
    }
    
}
