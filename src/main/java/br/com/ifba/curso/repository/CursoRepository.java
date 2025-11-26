/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.repository;

import br.com.ifba.curso.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ricle
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
    
    // Busca um Curso pelo atributo codigoCurso da entidade
    Curso findByCodigoCurso(String codigo);
}
