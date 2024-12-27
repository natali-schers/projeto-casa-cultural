package com.senacead.casacultural.model;
import lombok.Data;

@Data // Anotação que pertence ao Lombok que gera os métodos get/set e construtores
public class Analise {
    public int Id;
    
    public int IdFilme;
    
    public String Analise;
    
    public double nota;
}
