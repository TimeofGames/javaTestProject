package com.example.football.core.role;

import com.example.football.core.role.web.RoleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
}
