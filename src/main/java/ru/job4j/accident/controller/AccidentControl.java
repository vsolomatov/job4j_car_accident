package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("rules", accidentService.findAllRules());
        return "accident/create";
    }

    @GetMapping("/edit")
    // Аннотация @RequestParam позволяет получить параметр из строки запроса
    public String edit(@RequestParam int id, Model model) {
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("rules", accidentService.findAllRules());
        model.addAttribute("accident", accidentService.findByIdAccident(id));
        return "accident/edit";
    }

    @PostMapping("/save")
    // Аннотация @ModelAttribute говорит Spring брать параметры из запроса
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        // В формах (create.jsp, edit.jsp) указывается имя элемента select как "rIds", поэтому в запросе параметр будет иметь имя rIds
        String[] ids = req.getParameterValues("rIds");
        accidentService.save(accident, ids);
        return "redirect:/";
    }
}