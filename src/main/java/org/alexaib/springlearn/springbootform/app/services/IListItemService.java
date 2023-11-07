package org.alexaib.springlearn.springbootform.app.services;

import java.util.List;

public interface IListItemService<T> {
    List<T> list();

    T getById(int id);
}
