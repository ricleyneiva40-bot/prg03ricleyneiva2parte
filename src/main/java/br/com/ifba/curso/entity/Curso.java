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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ricle
 */
@Entity
@Table(name = "Cursos")
@Data //Pra gerar os Getters e Setters e demais comando do Data
@NoArgsConstructor //Anotação pra criar o Construtor vazio
@AllArgsConstructor //Anotação pra cirar o Construtor com os atributos
public class Curso extends PersistenceEntity implements Serializable{
    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "Ativo") 
    private boolean ativo;

   
}
