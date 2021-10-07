package com.works.repositories;

import com.works.entities.PetColor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetColorRepository extends JpaRepository<PetColor, Integer> {

    List<PetColor> findByOrderByPcidAsc(Pageable pageable);


}
