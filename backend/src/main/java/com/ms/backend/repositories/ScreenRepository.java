package com.ms.backend.repositories;

import com.ms.backend.entities.Screen;
import com.ms.backend.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    List<Screen> findByTheater(Theater theater);
}
