package com.example.forage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.forage.entity.Alerte;
import com.example.forage.repository.AlerteRepository;
import java.util.List;

@Controller
@RequestMapping("/alertes")
public class AlerteController {

    @Autowired
    private AlerteRepository alerteRepository;

    @GetMapping
    public String listAlertes(Model model) {
        List<Alerte> alertes = alerteRepository.findAll();
        model.addAttribute("alertes", alertes);
        return "alerte/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteAlerte(@PathVariable Long id) {
        alerteRepository.deleteById(id);
        return "redirect:/alertes";
    }
}
