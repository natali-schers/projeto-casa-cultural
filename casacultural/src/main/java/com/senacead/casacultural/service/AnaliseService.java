package com.senacead.casacultural.service;

import com.senacead.casacultural.model.Analise;
import com.senacead.casacultural.repository.AnaliseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {

    @Autowired
    AnaliseRepository analiseRepository;

    public Analise Cadastrar(Analise analise) {
        analiseRepository.save(analise);
        return analise;
    }

    public List<Analise> GetAnalises() {
        return analiseRepository.findAll();
    }

    public Analise GetAnaliseById(Integer id) {
        return analiseRepository.findById(id).orElseThrow();
    }

    public Analise AtualizarAnalise(Integer id, Analise analiseAtualizada) {
        Analise analise = GetAnaliseById(id);

        analise.setId(analise.getId());
        analise.setAnalise(analiseAtualizada.analise);
        analise.setNota(analiseAtualizada.nota);
        analise.setFilme(analise.getFilme());

        analiseRepository.save(analise);

        return analise;
    }

    public void ExcluirAnaliseById(Integer id) {
          Analise analise = GetAnaliseById(id);
        
        if (analise != null) {
           analiseRepository.deleteById(analise.id);
        }
    }
    
    public List<Analise> GetAnalisesByFilmeId(Integer FilmeId) {
        return analiseRepository.findByFilmeId(FilmeId);
    }
}
