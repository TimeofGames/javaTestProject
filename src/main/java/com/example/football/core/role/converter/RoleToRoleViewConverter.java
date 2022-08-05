package com.example.football.core.role.converter;

import com.example.football.core.role.Role;
import com.example.football.core.role.web.RoleView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToRoleViewConverter implements Converter<Role, RoleView> {

    @Override
    public RoleView convert(Role role) {
        RoleView view = new RoleView();
        view.setId(role.getId());
        view.setRole(role.getRole());
        return view;
    }
}
