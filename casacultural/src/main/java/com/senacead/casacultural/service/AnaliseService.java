package com.senacead.casacultural.service;

import com.senacead.casacultural.model.Analise;
import com.senacead.casacultural.repository.AnaliseRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {

    @Autowired
    AnaliseRepository analiseRepository;

    public Analise Cadastrar(Analise analise) {
        analise.setId(null);
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
        analise.setAnaliseFilme(analiseAtualizada.analiseFilme);
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

    @Transactional
    public void ExcluirAnalisesByFilmeId(Integer FilmeId) {
        List<Analise> analises = analiseRepository.findByFilmeId(FilmeId);

        if (analises != null) {
            analiseRepository.deleteByFilmeId(FilmeId);
        }
    }

    public List<Analise> GetAnalisesByFilmeId(Integer FilmeId) {
        List<Analise> analises = analiseRepository.findByFilmeId(FilmeId);
        return analises != null ? analises : new ArrayList<>();

    }
}
