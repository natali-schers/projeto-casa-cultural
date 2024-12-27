package com.senacead.casacultural.controller;

import com.senacead.casacultural.model.Filme;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmeController {
    private List<Filme> filmes = new ArrayList<>();
         
    @GetMapping("/Filme")
    public String ListarFilmes(Model model) {
        model.addAttribute("listaFilmes", filmes);
        return "/Filme/Index";
    }
    
    @GetMapping("/Filme/Cadastro")
    public String Cadastro (Model model) {
        model.addAttribute("filme", new Filme());
        return "/Filme/Cadastro";
    }
    
    @PostMapping("/cadastrar")
    public String ProcessarFormularioCadastro (@ModelAttribute Filme filme, Model model) {
        filme.setId(filmes.size() + 1);
        filmes.add(filme);
    
        return "redirect:/Filme";   
    }
}
