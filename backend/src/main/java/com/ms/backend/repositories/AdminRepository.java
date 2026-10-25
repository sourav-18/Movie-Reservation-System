package com.ms.backend.repositories;

import com.ms.backend.entities.Admin;
import com.ms.backend.entities.TheaterAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
