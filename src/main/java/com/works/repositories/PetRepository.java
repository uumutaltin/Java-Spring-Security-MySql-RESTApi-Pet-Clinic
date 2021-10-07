package com.works.repositories;

import com.works.entities.Pet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findByOrderByPidAsc(Pageable pageable);


}
