package org.alexaib.springlearn.springbootform.app.controllers;

import org.alexaib.springlearn.springbootform.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        return "form";
    }

    @PostMapping("/form")
    public String processForm(Model model,
                              @RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email) {
        model.addAttribute("user", new User(username, password, email));
        return "result";
    }

}
