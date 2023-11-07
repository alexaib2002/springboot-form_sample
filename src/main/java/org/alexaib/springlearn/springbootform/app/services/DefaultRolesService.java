package org.alexaib.springlearn.springbootform.app.services;

import org.alexaib.springlearn.springbootform.app.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultRolesService implements IListItemService<Role> {

    private final List<Role> list;

    public DefaultRolesService() {
        list = List.of(
                new Role(1, "Administrator", "ROLE_ADMIN"),
                new Role(2, "User", "ROLE_USER"),
                new Role(3, "Moderator", "ROLE_MODERATOR")
        );
    }

    @Override
    public List<Role> list() {
        return list;
    }

    @Override
    public Role getById(int id) {
        return list.stream().filter(role -> role.getId() == id).findFirst().orElse(null);
    }
}
