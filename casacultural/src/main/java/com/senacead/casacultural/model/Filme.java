package com.senacead.casacultural.model;

import lombok.Data;

@Data // Anotação que pertence ao Lombok que gera os métodos get/set e construtores
public class Filme {
    public Integer Id;
    
    public String Titulo;
    
    public String Sinopse;
    
    public String Genero;
    
    public int AnoLancamento;
}
