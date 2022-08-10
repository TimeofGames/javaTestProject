package com.example.football.core.role;

import com.example.football.core.role.converter.RoleToRoleViewConverter;
import com.example.football.core.role.web.RoleView;
import com.example.football.error.EntityNotFoundException;
import com.example.football.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepo roleRepo;

    private final MessageUtil messageUtil;

    private final RoleToRoleViewConverter roleToRoleViewConverter;

    public RoleService(RoleRepo roleRepo, MessageUtil messageUtil, RoleToRoleViewConverter roleToRoleViewConverter) {
        this.roleRepo = roleRepo;
        this.messageUtil = messageUtil;
        this.roleToRoleViewConverter = roleToRoleViewConverter;
    }

    public Role getRoleById(int id) {
        return roleRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("role.NotFound", id)));
    }

    public RoleView getRole(int id) {
        return roleToRoleViewConverter.convert(getRoleById(id));
    }

    public Page<RoleView> getAllRoles(Pageable pageable) {
        Page<Role> roles = roleRepo.findAll(pageable);
        List<RoleView> roleViews = roles.stream()
                .map(roleToRoleViewConverter::convert)
                .collect(Collectors.toList());
        return new PageImpl<>(roleViews, pageable, roles.getTotalElements());
    }
}
