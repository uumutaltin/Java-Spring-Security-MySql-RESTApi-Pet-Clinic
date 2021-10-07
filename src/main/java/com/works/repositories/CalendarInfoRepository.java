package com.works.repositories;

import com.works.entities.CalendarInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarInfoRepository extends JpaRepository<CalendarInfo, Integer> {
    List<CalendarInfo> findByOrderByCidAsc(Pageable pageable);
}
