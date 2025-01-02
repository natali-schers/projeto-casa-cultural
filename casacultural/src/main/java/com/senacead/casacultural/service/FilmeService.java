package com.senacead.casacultural.service;

import com.senacead.casacultural.model.Filme;
import com.senacead.casacultural.repository.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {
    @Autowired
    FilmeRepository filmeRepository;
    
    public Filme Cadastrar(Filme filme) {
        filme.setId(null);
        filmeRepository.save(filme);
        return filme;
    }
    
    public List<Filme> GetFilmes() {
        return filmeRepository.findAll();
    }
    
    public Filme GetFilmeById(Integer id) {
        return filmeRepository.findById(id).orElseThrow();
    }
    
    public void ExcluirFilmeById(Integer id) {
        Filme filme = GetFilmeById(id);
        
        if (filme != null) {
           filmeRepository.deleteById(filme.id);
        }
    }
    
    public Filme AtualizarFilme(Integer id, Filme filmeAtualizado) {
        Filme filme = GetFilmeById(id);
        
        filme.setId(filme.getId());
        filme.setTitulo(filmeAtualizado.titulo);
        filme.setSinopse(filmeAtualizado.sinopse);
        filme.setGenero(filmeAtualizado.genero);
        filme.setAnoLancamento(filmeAtualizado.anoLancamento);
        
        filmeRepository.save(filme);
        
        return filme;
    }
}
