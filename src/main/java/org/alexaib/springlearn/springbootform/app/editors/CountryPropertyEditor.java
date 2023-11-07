package org.alexaib.springlearn.springbootform.app.editors;

import org.alexaib.springlearn.springbootform.app.model.Country;
import org.alexaib.springlearn.springbootform.app.services.IListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class CountryPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private IListItemService<Country> service;

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id == null || id.isEmpty() || id.isBlank())
            return;
        try {
            this.setValue(service.getById(Integer.parseInt(id)));
        } catch (NumberFormatException | NullPointerException ignored) { }
    }
}
