package com.works.restcontrollers;

import com.works.dto.DiaryDto;
import com.works.entities.Diary;
import com.works.entities.redis.DiaryRedis;
import com.works.repositories.redis.DiaryRedisRepository;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/diary")
public class DiaryRestController {

    final DiaryDto dDto;
    final Util util;
    final DiaryRedisRepository drRepo;
    public DiaryRestController(DiaryDto dDto, Util util, DiaryRedisRepository drRepo) {
        this.dDto = dDto;
        this.util = util;
        this.drRepo = drRepo;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  dDto.DiaryList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Diary diary, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();

        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return dDto.DiaryAdd(diary);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid Diary diary, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  dDto.updateDiary(diary);
        }
    }

    @DeleteMapping("/delete/{did}")
    public Map<ERest, Object> delete( @PathVariable String did){
        return dDto.deleteDiary(did);
    }

    @GetMapping("/redislist/{pageNo}")
    public Map<ERest, Object> redisList(@PathVariable String pageNo){
        return  dDto.diaryRedisList(pageNo);
    }


}
