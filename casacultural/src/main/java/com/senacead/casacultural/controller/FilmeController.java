package com.senacead.casacultural.controller;

import com.senacead.casacultural.model.Analise;
import com.senacead.casacultural.model.Filme;
import com.senacead.casacultural.service.AnaliseService;
import com.senacead.casacultural.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @Autowired
    AnaliseService analiseService;

    @GetMapping("/")
    public String ListarFilmes(Model model) {
        List<Filme> filmes = filmeService.GetFilmes();
        model.addAttribute("listaFilmes", filmes);
        return "/Filme/Index";
    }

    @GetMapping("/Filme/Cadastro")
    public String CadastrarFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "/Filme/Cadastro";
    }

    @PostMapping("/CadastrarFilme")
    public String ProcessarFormularioCadastroFilme(@ModelAttribute Filme filme, Model model) {
        if (filme.getId() != null && filmeService.GetFilmeById(filme.getId()) != null) {
            filmeService.AtualizarFilme(filme.getId(), filme);
        } else {
            filmeService.Cadastrar(filme);
        }

        return "redirect:/";
    }

    @GetMapping("/DetalhesFilme/{id}")
    public String DetalhesFilme(@PathVariable int id, Model model) {
        Filme filmeSelecionado = filmeService.GetFilmeById(id);

        if (filmeSelecionado == null) {
            return "redirect:/";
        }

        List<Analise> analisesFilmeSelecionado = analiseService.GetAnalisesByFilmeId(filmeSelecionado.getId());

        Analise analise = new Analise();
        analise.setFilme(filmeSelecionado);

        model.addAttribute("analise", analise);
        model.addAttribute("listaAnalises", analisesFilmeSelecionado);
        model.addAttribute("filme", filmeSelecionado);

        return "/Filme/Filme";
    }

    @GetMapping("/ExcluirFilme/{id}")
    public String ExcluirFilme(Model model, @PathVariable Integer id) {
        analiseService.ExcluirAnalisesByFilmeId(id);
        filmeService.ExcluirFilmeById(id);

        return "redirect:/";
    }

    @GetMapping("/AlterarFilme/{id}")
    public String AlterarFilme(Model model, @PathVariable Integer id) {
        model.addAttribute("filme", filmeService.GetFilmeById(id));
        return "/Filme/Cadastro";
    }

    @GetMapping("/{id}/CadastrarAnalise")
    public String CadastrarAnalise(Model model, @PathVariable String id) {
        Analise analise = new Analise();
        analise.setFilme(filmeService.GetFilmeById(Integer.parseInt(id)));
        model.addAttribute("analise", analise);

        return "Filme/Analise";
    }

    @PostMapping("/ProcessarAnalise")
    public String ProcessarFormularioCadastroAnalise(@ModelAttribute Analise analise, Model model) {
        Filme filme = filmeService.GetFilmeById(analise.getFilme().getId());
        analise.setFilme(filme);

        if (analise.getId() != null && analiseService.GetAnaliseById(analise.getId()) != null) {
            analiseService.AtualizarAnalise(analise.getId(), analise);
        } else {
            analiseService.Cadastrar(analise);
        }

        return "redirect:/DetalhesFilme/" + filme.getId();
    }

    @GetMapping("/AlterarAnalise/{id}")
    public String AlterarAnalise(Model model, @PathVariable Integer id) {
        model.addAttribute("analise", analiseService.GetAnaliseById(id));
        return "/Filme/Analise";
    }

    @GetMapping("/ExcluirAnalise/{id}")
    public String ExcluirAnalise(Model model, @PathVariable Integer id) {
        Analise analise = analiseService.GetAnaliseById(id);
        analiseService.ExcluirAnaliseById(id);

        return "redirect:/DetalhesFilme/" + analise.filme.getId();
    }

}
