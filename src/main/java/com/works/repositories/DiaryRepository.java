package com.works.repositories;

import com.works.entities.Diary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    List<Diary> findByOrderByDidAsc(Pageable pageable);

}
