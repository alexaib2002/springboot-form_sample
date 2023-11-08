package org.alexaib.springlearn.springbootform.app.controllers;

import jakarta.validation.Valid;
import org.alexaib.springlearn.springbootform.app.editors.CapsNameEditor;
import org.alexaib.springlearn.springbootform.app.editors.CountryPropertyEditor;
import org.alexaib.springlearn.springbootform.app.editors.RolesPropertyEditor;
import org.alexaib.springlearn.springbootform.app.model.Country;
import org.alexaib.springlearn.springbootform.app.model.Role;
import org.alexaib.springlearn.springbootform.app.model.User;
import org.alexaib.springlearn.springbootform.app.services.IListItemService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("user")
public class FormController {

    @Autowired
    private UserValidator validator;

    @Autowired
    private IListItemService<Country> countryService;

    @Autowired
    private IListItemService<Role> rolesService;

    @Autowired
    private CountryPropertyEditor countryPropertyEditor;

    @Autowired
    private RolesPropertyEditor rolesPropertyEditor;

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
        binder.registerCustomEditor(Country.class, "country", countryPropertyEditor);
        binder.registerCustomEditor(Role.class, "roles", rolesPropertyEditor);
    }

    @ModelAttribute("genders")
    public List<String> gender() {
        return Arrays.asList("Masculine", "Feminine");
    }

    @ModelAttribute("roles")
    public List<Role> roles() {
        return rolesService.list();
    }

    @ModelAttribute("countries")
    public List<Country> countries() {
        return countryService.list();
    }

    @GetMapping("/form")
    public String form(Model model) {
        User user = new User();
        user.setIdx("14AB");
        user.setName("John");
        user.setSurname("Doe");
        user.setEnabled(false);
        user.setSecretValue("********");
        user.setCountry(countryService.getById(1)); // set default country
        user.setRoles(Arrays.asList(rolesService.getById(2))); // set default role
        model.addAttribute("user", user);

        return "form";
    }

    /**
     *
     * @param user User fetched from HTTP session via SessionAttribute
     * @param model MVC view
     * @param status HTTP session controller
     * @return Result view
     */
    @GetMapping("result")
    public String result(@SessionAttribute(name = "user", required = false) User user, Model model, SessionStatus status) {
        if (user == null)
            return "redirect:/form";
        status.setComplete();
        model.addAttribute("user", user);
        return "result";
    }

    /**
     * @param user   Validated java object
     * @param result Must be after the validated object
     * @return Result webpage
     */
    @PostMapping("/form")
    public String processForm(@Valid User user, BindingResult result) {
//        validator.validate(user, result); // explicit validation
        if (result.hasErrors())
            return "form";
        return "redirect:/result";
    }

}
