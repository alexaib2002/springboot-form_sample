package org.alexaib.springlearn.springbootform.app.services;

import org.alexaib.springlearn.springbootform.app.model.Country;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DefaultCountryService implements ICountryService {

    private final List<Country> list;

    public DefaultCountryService() {
        this.list = Arrays.asList(
                new Country(1, "ES", "España"),
                new Country(2, "MX", "México"),
                new Country(3, "CL", "Chile"),
                new Country(4, "AR", "Argentina"),
                new Country(5, "PE", "Perú"));
    }

    @Override
    public List<Country> list() {
        return list;
    }

    @Override
    public Country getById(int id) {
        return list.stream().filter(country -> country.getId() == id).findFirst().orElse(null);
    }
}
