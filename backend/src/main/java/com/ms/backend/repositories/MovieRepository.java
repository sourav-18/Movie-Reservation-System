package com.ms.backend.repositories;

import com.ms.backend.entities.Movie;
import com.ms.backend.entities.types.MovieStatus;
import com.ms.backend.entities.types.TheaterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Modifying
    @Query("""
           UPDATE Movie M SET M.status=:status WHERE M.id=:id
           """)
    Integer statusUpdate(
            @Param("id") Long id,
            @Param("status") MovieStatus status);
}
