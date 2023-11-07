package org.alexaib.springlearn.springbootform.app.controllers;

import jakarta.validation.Valid;
import org.alexaib.springlearn.springbootform.app.editors.CapsNameEditor;
import org.alexaib.springlearn.springbootform.app.model.Country;
import org.alexaib.springlearn.springbootform.app.model.User;
import org.alexaib.springlearn.springbootform.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("user")
public class FormController {

    @Autowired
    private UserValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator); // adds new validator to the Spring Web Validators stack
        binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(
                // Init object via lambda
                Optional.of(new SimpleDateFormat("yyyy-MM-dd")).map(f -> {
                    f.setLenient(false);
                    return f;
                }).get(), false));
        // Apply CapsNameEditor editor for both name and surname fields
        List.of("name", "surname").forEach(field ->
                binder.registerCustomEditor(String.class, field, new CapsNameEditor()));
    }

    @ModelAttribute("countries")
    public List<Country> countries() {
        return Arrays.asList(
                new Country(1, "ES", "España"),
                new Country(2, "MX","México"),
                new Country(3, "CL","Chile"),
                new Country(4, "AR","Argentina"),
                new Country(5, "PE","Perú"));
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
