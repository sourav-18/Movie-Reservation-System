package com.ms.backend.repositories;

import com.ms.backend.entities.TheaterAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterAdminRepository extends JpaRepository<TheaterAdmin,Long> {
    Optional<TheaterAdmin> findByEmail(String email);
}
