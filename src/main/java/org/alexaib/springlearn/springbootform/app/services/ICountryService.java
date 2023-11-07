package org.alexaib.springlearn.springbootform.app.services;

import org.alexaib.springlearn.springbootform.app.model.Country;

import java.util.List;

public interface ICountryService {
    List<Country> list();

    Country getById(int id);
}
