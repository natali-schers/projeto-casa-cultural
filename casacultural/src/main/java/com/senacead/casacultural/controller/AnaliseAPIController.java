package com.senacead.casacultural.controller;

import com.senacead.casacultural.model.Analise;
import com.senacead.casacultural.service.AnaliseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filme/analise")
public class AnaliseAPIController {
    @Autowired
    AnaliseService analiseService;
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Analise> Cadastrar(@RequestBody Analise analise) {
        Analise novaAnalise = analiseService.Cadastrar(analise);
        
        return new ResponseEntity<>(novaAnalise, HttpStatus.CREATED);
    }
    
    @GetMapping("/{filmeId}")
    public ResponseEntity<List> GetAnalisesByFilmeId(@PathVariable Integer filmeId) {
        List<Analise> analises = analiseService.GetAnalisesByFilmeId(filmeId);
        
        return new ResponseEntity<>(analises, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Analise> AtualizarAnalise(@PathVariable Integer id, @RequestBody Analise analiseAtualizada) {
        Analise analise = analiseService.GetAnaliseById(id);
        
        analiseService.AtualizarAnalise(id, analiseAtualizada);
        
        return new ResponseEntity<>(analise, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> ExcluirAnalise(@PathVariable Integer id) {
       analiseService.ExcluirAnaliseById(id);
       return new ResponseEntity<>("Análise excluída com sucesso!", HttpStatus.OK);
    }
    
    
}
