package com.example.forage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.forage.entity.*;
import com.example.forage.service.*;
import java.util.List;

@Controller
@RequestMapping("/parametres")
public class ParametreController {

    @Autowired
    private ParametreService parametreService;

    @Autowired
    private StatusService statusService;

    @GetMapping
    public String listParametres(Model model) {
        List<Parametre> parametres = parametreService.getAllParametres();
        model.addAttribute("parametres", parametres);
        return "parametre/list";
    }

    @GetMapping("/new")
    public String createParametre(Model model) {
        model.addAttribute("parametre", new Parametre());
        model.addAttribute("statuses", statusService.getAllStatuses());
        return "parametre/form";
    }

    @GetMapping("/{id}/edit")
    public String editParametre(@PathVariable Long id, Model model) {
        Parametre parametre = parametreService.getParametreById(id);
        if (parametre == null) {
            return "redirect:/parametres";
        }
        model.addAttribute("parametre", parametre);
        model.addAttribute("statuses", statusService.getAllStatuses());
        return "parametre/form";
    }

    @PostMapping("/save")
    public String saveParametre(@ModelAttribute Parametre parametre) {
        parametreService.saveParametre(parametre);
        return "redirect:/parametres";
    }

    @GetMapping("/{id}/delete")
    public String deleteParametre(@PathVariable Long id) {
        parametreService.deleteParametre(id);
        return "redirect:/parametres";
    }
}
