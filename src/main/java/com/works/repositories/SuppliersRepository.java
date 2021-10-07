package com.works.repositories;


import com.works.entities.Suppliers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuppliersRepository extends JpaRepository<Suppliers, Integer> {

    List<Suppliers> findByOrderBySidAsc(Pageable pageable);

}
