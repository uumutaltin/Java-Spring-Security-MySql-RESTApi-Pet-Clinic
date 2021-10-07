package com.works.repositories;

import com.works.entities.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Logger, Integer> {





}
