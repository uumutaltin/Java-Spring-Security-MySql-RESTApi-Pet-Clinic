package com.works.repositories;

import com.works.entities.Image;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    List<Image> findByOrderByIidAsc(Pageable pageable);



}
