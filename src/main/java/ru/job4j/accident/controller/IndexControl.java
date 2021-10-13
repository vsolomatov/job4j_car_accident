package ru.job4j.accident.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;

@Controller
/*
    Аннотации @Repository, @Service, @Controller представляют собой частные случаи аннотации @Component.
    Об области их применения указывают названия:
        @Repository служит для обозначения хранилищ данных,
        @Service для классов с бизнес логикой,
        @Controller для обработчиков запросов
 */
public class IndexControl {

    private final AccidentService accidentService;

    public IndexControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    /*
        Метод index принимает объект Model. Это аналог HttpServletRequest, в который также можно добавить параметры.
     */
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        // общение контроллера и repository через слой сервис, а не напрямую
        model.addAttribute("accidents", accidentService.findAllAccidents());
        return "index";
    }
}
