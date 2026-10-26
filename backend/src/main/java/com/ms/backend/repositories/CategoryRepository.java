package com.ms.backend.repositories;

import com.ms.backend.entities.Category;
import com.ms.backend.entities.types.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
//    List<Category>findActiveCategory()
}
