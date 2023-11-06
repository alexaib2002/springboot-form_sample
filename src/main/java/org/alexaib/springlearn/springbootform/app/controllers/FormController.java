package org.alexaib.springlearn.springbootform.app.controllers;

import jakarta.validation.Valid;
import org.alexaib.springlearn.springbootform.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class FormController {

    private User user;

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", user);
        return "form";
    }

    /**
     * @param user   Validated java object
     * @param result Must be after the validated object
     * @param model  MVC view
     * @return Result webpage
     */
    @PostMapping("/form")
    public String processForm(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", result.getFieldErrors()
                    .stream().collect(Collectors.toMap(FieldError::getField, f ->
                        String.format("Field %s %s", f.getField(), f.getDefaultMessage())
                    )));
            return "form";
        }
        model.addAttribute("user", user);
        return "result";
    }

}
