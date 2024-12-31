package com.senacead.casacultural.controller;

import com.senacead.casacultural.model.Analise;
import com.senacead.casacultural.model.Filme;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmeController {

    private List<Filme> filmes = new ArrayList<>();
    private List<Analise> analises = new ArrayList<>();

    @GetMapping("/")
    public String ListarFilmes(Model model) {
        model.addAttribute("listaFilmes", filmes);
        return "/Filme/Index";
    }

    @GetMapping("/Filme/Cadastro")
    public String Cadastro(Model model) {
        model.addAttribute("filme", new Filme());
        return "/Filme/Cadastro";
    }

    @PostMapping("/CadastrarFilme")
    public String ProcessarFormularioCadastroFilme(@ModelAttribute Filme filme, Model model) {
        filme.setId(filmes.size() + 1);
        filmes.add(filme);

        return "redirect:/";
    }

    @PostMapping("/CadastrarAnalise")
    public String ProcessarFormularioCadastroAnalise(@ModelAttribute Analise analise, Model model) {
        analise.setId(analises.size() + 1);
        analises.add(analise);

        return "redirect:/";
    }

    @GetMapping("/DetalhesFilme/{id}")
    public String DetalhesFilme(@PathVariable("id") int id, Model model) {
        Filme filmeSelecionado = filmes.stream()
                .filter(filme -> filme.getId() == id)
                .findFirst()
                .orElse(null);

        List<Analise> analisesFilmeSelecionado = analises.stream()
                .filter(analise -> analise.IdFilme == filmeSelecionado.Id).toList();
        
        if (filmeSelecionado == null) {
            return "redirect:/";
        }

        Analise analise = new Analise();
        analise.setIdFilme(filmeSelecionado.Id);
        
        model.addAttribute("analise", analise);
        model.addAttribute("listaAnalises", analisesFilmeSelecionado);
        model.addAttribute("filme", filmeSelecionado);
        return "/Filme/Filme";
    }
}
