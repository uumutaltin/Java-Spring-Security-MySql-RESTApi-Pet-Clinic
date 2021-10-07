package com.works.repositories;

import com.works.entities.Lab;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab, Integer> {

    List<Lab> findByOrderByLidAsc(Pageable pageable);

}
