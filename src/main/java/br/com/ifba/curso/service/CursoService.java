/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;


import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil;
import java.util.List;
import org.springframework.stereotype.Service;
import br.com.ifba.curso.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author ricle
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CursoService implements CursoIService{

    
    private final CursoRepository  repository;//Reduz o acoplamento e deixa o codigo mais limpo
    
    
    @Override
    public void save(Curso curso) throws IllegalArgumentException{
        //O Curso deve ser valido para Salvar
        if(curso == null){
            log.error("Erro ao salvar um curso nulo");
            throw new IllegalArgumentException("Dados do curso não podem ser nulos");
        }
       
        //Obriga o usuario a ter um nome
        if(StringUtil.isNullOrEmpty(curso.getNome())){
            log.error("Nome invalido ao salvar o curso");
            throw new IllegalArgumentException("O nome do Curso é obrigatorio");
        }
        
        if(StringUtil.isNullOrEmpty(curso.getCodigo())){
            log.error("Codigo invalido ao salvar");
            throw new IllegalArgumentException("O Codigo do Curso é obrigatorio");
        }
        
        //Retira todos os espacos que nao tem importancia
        curso.setNome(StringUtil.normalize(curso.getNome()));
        curso.setCodigo(StringUtil.normalize(curso.getCodigo()));
        
        //Logger que salva com sucesso o Curso
        log.info("Salvando o Curso: codigo = {}, nome = {}", curso.getCodigo(), curso.getNome());
        //Se todas as validacoes estiverem corretas, salva o curso
        repository.save(curso);
        
    }

    @Override
    public void update(Curso curso) throws  IllegalArgumentException{
        //objeto e o Id devem ser validos
        if(curso == null || curso.getId() == null || curso.getId() <= 0){
            log.error("Erro ao atualizar curso, algo esta invalido");
            throw new IllegalArgumentException("Impossivel atualizar um curso com o Id invalido");
        } 
        
        //Validacoes da regra de negócio
        if(StringUtil.isNullOrEmpty(curso.getNome())){
            log.error("Nome invalido ao atualizar o curso");
            throw new IllegalArgumentException("Nome do Curso é obrigatorio");
        }
        
        if(StringUtil.isNullOrEmpty(curso.getCodigo())){
            log.error("Codigo invalido ao atualizar curso");
            throw new IllegalArgumentException("Codigo do Curso é obrigatório");
        }
        
            //Normalização de Textos
            curso.setNome(StringUtil.normalize(curso.getNome()));
            curso.setCodigo(StringUtil.normalize(curso.getCodigo()));
            
            log.info("Atualizando Curso: codigo = {}, nome = {}", curso.getCodigo(), curso.getNome());
            //Atualiza no banco
            repository.save(curso);      
    }

    @Override
    public void delete(Curso curso) throws  IllegalArgumentException{
        //Verificação do Curso para a remoção
        if(curso == null || curso.getId() == null || curso.getId() <= 0){
            log.error("Erro ao deletar curso. Curso nulo ou ID invalido");
            throw new IllegalArgumentException("Dados do curso não preenchidos");
        }
        else {
            //log da remoção
            log.info("Deletando Curso: codigo = {}, nome = {}", curso.getCodigo(), curso.getNome());
            
            //Se tudo der certo, exclui o curso
            repository.delete(curso);
        }
    }

    @Override
    public List<Curso> findAll(){
        //Listando Cursos
        log.info("Listando Cursos");
        //Lista os Cursos
        return repository.findAll();
    }

    @Override
    public Curso findById(Long id) throws  IllegalArgumentException{
        //Vericação do ID
        if(id == null || id <= 0){
            log.error("Erro ao buscar curso: ID invalido {}", id);
            throw new IllegalArgumentException("Id invalido para fazer a busca!");
        }
        //Buscando pelo ID
        log.info("Buscando o curso pelo ID = {}", id);
        //retorna a busca feita pelo ID
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Curso> findByCodigo(String codigo)  throws RuntimeException{
        if (StringUtil.isNullOrEmpty(codigo)) {
        log.error("Tentativa de buscar curso por código inválido: {}", codigo);
        throw new IllegalArgumentException("Código inválido para busca.");
    }
           //Buscando o curso por codigo 
           log.info("Buscando curso pelo código = {}", codigo);
        
        //Busca pelo codigo utilizando o Repository
        Curso curso = repository.findByCodigo(codigo); 
        if(curso != null){
            return List.of(curso); 
        }
        return List.of();
    }
    
}
