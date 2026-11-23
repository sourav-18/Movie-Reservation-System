package com.ms.backend.repositories;

import com.ms.backend.entities.Theater;
import com.ms.backend.entities.types.TheaterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TheaterRepository extends JpaRepository<Theater,Long> {

   @Modifying
   @Query("""
           UPDATE Theater T SET T.status=:status WHERE T.id=:id
           """)
   Integer statusUpdate(
                        @Param("id") Long theaterId,
                        @Param("status") TheaterStatus status);
}
