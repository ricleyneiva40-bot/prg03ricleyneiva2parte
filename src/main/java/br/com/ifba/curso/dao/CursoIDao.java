/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ricle
 */
@Repository
public interface CursoIDao extends JpaRepository<Curso, Long>{
    
    @Query("SELECT c FROM Curso c WHERE c.codigoCurso = :codigo")
    Curso buscarPorCodigo(String codigo);
}
