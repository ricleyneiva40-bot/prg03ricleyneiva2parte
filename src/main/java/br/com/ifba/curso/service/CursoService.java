/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;


import br.com.ifba.curso.dao.CursoIDao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ricle
 */
@Service
public class CursoService implements CursoIService{

    @Autowired
    private CursoIDao  cursoDao;//Reduz o acoplamento e deixa o codigo mais limpo
    
    
    @Override
    public void save(Curso curso) throws IllegalArgumentException{
        //O Curso deve ser valido para Salvar
        if(curso == null){
            throw new IllegalArgumentException("Dados do curso não podem ser nulos");
        }
       
        if(StringUtil.isNullOrEmpty(curso.getNome())){
            throw new IllegalArgumentException("O nome do Curso é obrigatorio");
        }
        
        if(StringUtil.isNullOrEmpty(curso.getCodigo())){
            throw new IllegalArgumentException("O Codigo do Curso é obrigatorio");
        }
        
        //Retira todos os espacos que nao tem importancia
        curso.setNome(StringUtil.normalize(curso.getNome()));
        curso.setCodigo(StringUtil.normalize(curso.getCodigo()));
        
        //Se todas as validacoes estiverem corretas, salva o curso
        cursoDao.save(curso);
        
    }

    @Override
    public void update(Curso curso) throws  IllegalArgumentException{
        //objeto e o Id devem ser validos
        if(curso == null || curso.getId() == null || curso.getId() <= 0){
            throw new IllegalArgumentException("Impossivel atualizar um curso com o Id invalido");
        } 
        
        //Validação especifica
        if(curso.getId() == null || curso.getId() <= 0){
            throw new IllegalArgumentException("Não é Possivel atualizar um curso com Id invalido");
        }
        
        //Validacoes da regra de negócio
        if(StringUtil.isNullOrEmpty(curso.getNome())){
            throw new IllegalArgumentException("Nome do Curso é obrigatorio");
        }
        
        if(StringUtil.isNullOrEmpty(curso.getCodigo())){
            throw new IllegalArgumentException("Codigo do Curso é obrigatório");
        }
        
            //Normalização
            curso.setNome(StringUtil.normalize(curso.getNome()));
            curso.setCodigo(StringUtil.normalize(curso.getCodigo()));
            
            cursoDao.save(curso);      
    }

    @Override
    public void delete(Curso curso) throws  IllegalArgumentException{
        //Verificação do Curso para a remoção
        if(curso == null){
            throw new IllegalArgumentException("Dados do curso não preenchidos");
        }
        else {
            cursoDao.delete(curso);
        }
    }

    @Override
    public List<Curso> findAll(){
        return cursoDao.findAll();
    }

    @Override
    public Curso findById(Long id) throws  IllegalArgumentException{
        //Vericação do ID
        if(id == null || id <= 0){
            throw new IllegalArgumentException("Id invalido para fazer a busca!");
        }
        return cursoDao.findById(id).orElse(null);
    }

    @Override
    public List<Curso> findByCodigo(String codigo)  throws RuntimeException{
        Curso curso = cursoDao.buscarPorCodigo(codigo); if(curso != null){ return List.of(curso); 
        }
        return List.of();
    }
    
}
