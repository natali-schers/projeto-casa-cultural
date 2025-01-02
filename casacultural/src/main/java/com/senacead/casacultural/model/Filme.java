package com.senacead.casacultural.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Anotação que pertence ao Lombok que gera os métodos get/set e construtores
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Filme")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    
    public String titulo;
    
    public String sinopse;
    
    public String genero;
    
    public int anoLancamento;
}
