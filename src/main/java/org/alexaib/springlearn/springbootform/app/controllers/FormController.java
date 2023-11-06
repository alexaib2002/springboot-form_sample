package org.alexaib.springlearn.springbootform.app.controllers;

import jakarta.validation.Valid;
import org.alexaib.springlearn.springbootform.app.model.User;
import org.alexaib.springlearn.springbootform.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("user")
public class FormController {

    @Autowired
    private UserValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator); // adds new validator to the Spring Web Validators stack
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new User(
                "14AB",
                "John",
                "Doe",
                "",
                "",
                ""));
        return "form";
    }

    /**
     * @param user   Validated java object
     * @param result Must be after the validated object
     * @param model  MVC view
     * @return Result webpage
     */
    @PostMapping("/form")
    public String processForm(@Valid User user, BindingResult result, Model model, SessionStatus status) {
//        validator.validate(user, result); // explicit validation
        if (result.hasErrors())
            return "form";
        model.addAttribute("user", user);
        status.setComplete();
        return "result";
    }

}
