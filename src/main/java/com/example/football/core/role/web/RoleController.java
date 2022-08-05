package com.example.football.core.role.web;

import com.example.football.core.role.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RoleView getRoleById(@PathVariable(name = "id") int id) {
        return roleService.getRole(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public Page<RoleView> getAllRoles(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return roleService.getAllRoles(pageable);
    }
}
