package com.senacead.casacultural.controller;

import com.senacead.casacultural.model.Filme;
import com.senacead.casacultural.service.FilmeService;
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
@RequestMapping("/filme")
public class FilmeAPIController {
    @Autowired
    FilmeService filmeService;
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Filme> Cadastrar(@RequestBody Filme filme) {
        Filme novoFilme = filmeService.Cadastrar(filme);
        
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }   
    
    @GetMapping("/listar")
    public ResponseEntity<List> GetFilmes() {
        List<Filme> filmes = filmeService.GetFilmes();
        
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }
    
    @GetMapping("/detalhes/{id}")
    public ResponseEntity<Filme> DetalhesFilme(@PathVariable Integer id) {
        Filme filme = filmeService.GetFilmeById(id);
        
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Filme> AtualizarFilme(@PathVariable Integer id, @RequestBody Filme filmeAtualizado) {
        Filme filme = filmeService.GetFilmeById(id);
        
        filmeService.AtualizarFilme(id, filmeAtualizado);
        
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> ExcluirFilme(@PathVariable Integer id) {
        filmeService.ExcluirFilmeById(id);
        return new ResponseEntity<>("Filme excluído com sucesso!", HttpStatus.OK);  
    }
}