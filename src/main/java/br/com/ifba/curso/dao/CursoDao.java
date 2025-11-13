/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import jakarta.persistence.EntityManager;

/**
 *
 * @author ricle
 */
public class CursoDao extends GenericDao<Curso> implements CursoIDao{

    public CursoDao() {
        super();
    }

    
    @Override
    public Curso buscarPorCodigo(String codigo) {
         EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT c FROM Curso c WHERE c.codigoCurso = :codigo";
            Curso curso = entityManager.createQuery(jpql, Curso.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            return curso;

        } catch (jakarta.persistence.NoResultException e) {
            return null;
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso por código: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }
    
    
}
