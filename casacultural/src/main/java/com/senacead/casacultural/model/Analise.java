package com.senacead.casacultural.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Anotação que pertence ao Lombok que gera os métodos get/set e construtores
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Analise")
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    
    @ManyToOne
    @JoinColumn(name = "FilmeId", referencedColumnName = "Id")
    public Filme filme;    
    
    public String analiseFilme;
    
    public double nota;
}
