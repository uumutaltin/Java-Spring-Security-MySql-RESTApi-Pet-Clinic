package com.works.repositories.redis;

import com.works.entities.redis.DiaryRedis;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;


@EnableRedisRepositories
public interface DiaryRedisRepository extends CrudRepository<DiaryRedis,Integer> {


}
