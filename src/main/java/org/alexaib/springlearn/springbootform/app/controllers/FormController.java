package org.alexaib.springlearn.springbootform.app.controllers;

import org.alexaib.springlearn.springbootform.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        return "form";
    }

    @PostMapping("/form")
    public String processForm(User user, Model model) {
        model.addAttribute("user", user);
        return "result";
    }

}
