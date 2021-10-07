package com.works.repositories;

import com.works.entities.PetRace;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRaceRepository extends JpaRepository<PetRace, Integer> {

    List<PetRace> findByOrderByRidAsc(Pageable pageable);

}
