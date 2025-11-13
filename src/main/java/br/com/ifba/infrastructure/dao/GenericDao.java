/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author ricle
 */
public class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {

    protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gerenciamento_curso");
    
    @Override
    //metodo salvar implementado do GenericIDao
    public Entity save(Entity obj) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            
            entityManager.getTransaction().begin();
            //Salva
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            return obj;
            
        } catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback(); //Desfaz tudo
            }
            System.err.println("Erro ao salvar a Entidade: "+ e.getMessage());
            throw e;
            
    } finally{
        entityManager.close();
    }
        
    }

    @Override
    //Atualizar curso
    public Entity update(Entity obj) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            
            entityManager.merge(obj);
            
            entityManager.getTransaction().commit();
            return obj;
            
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao Atualizar a Entidade: "+ e.getMessage());
            throw e;
        } finally{
            entityManager.close();
        }
        
    }

    @Override
    //Metodo de Remover
    public void delete(Entity obj) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            
            entityManager.getTransaction().begin();
            
            Entity entityEncontrada = entityManager.merge(obj);
            
            entityManager.remove(entityEncontrada);
            
            entityManager.getTransaction().commit();
            
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao excluir a Entidade: "+ e.getMessage());
            throw e;
            
        } finally{
            entityManager.close();
        }
        
    }

    @Override
    //Metodo de listar
    public List<Entity> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            return entityManager.createQuery(("from "+ getTypeClass().getName())).getResultList();
        }catch(Exception e){
            
            System.err.println("Erro ao listar as Entidades: "+ e.getMessage());
            return null;
        }finally{
            entityManager.close();
        }
    }

    @Override
    //Metodo de nuscar pelo Id
    public Entity findByLd(Long id) {
       EntityManager entityManager = entityManagerFactory.createEntityManager();
       
       try{
           return (Entity) entityManager.find(getTypeClass(), id);
       }finally{
           entityManager.close();
       }
        
        
    }

    protected Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }
    
}
