package com.works.repositories;

import com.works.entities.WareHouse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {

    List<WareHouse> findByOrderByWidAsc(Pageable pageable);
}
