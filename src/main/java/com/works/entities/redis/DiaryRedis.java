package com.works.entities.redis;

import com.works.entities.base.BaseDiary;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Diary")
public class DiaryRedis extends BaseDiary {

}
