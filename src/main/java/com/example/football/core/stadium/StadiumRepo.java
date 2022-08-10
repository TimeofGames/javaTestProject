package com.example.football.core.stadium;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepo extends JpaRepository<Stadium, Long> {
}
