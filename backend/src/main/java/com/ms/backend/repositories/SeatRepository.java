package com.ms.backend.repositories;

import com.ms.backend.dtos.DbInternal.RowColDbDto;
import com.ms.backend.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
   @Query("""
       SELECT s.rowNumber AS rowNumber,
              s.columnNumber AS columnNumber
       FROM Seat s
       WHERE s.screen.id = :screenId
       """)
   List<RowColDbDto> getRowAndColumnByScreenId(
           @Param("screenId") Long screenId);
}
