package com.works.dto;

import com.works.config.Config;
import com.works.entities.Diary;
import com.works.entities.redis.DiaryRedis;
import com.works.repositories.DiaryRepository;
import com.works.repositories.redis.DiaryRedisRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiaryDto {

    final DiaryRepository dRepo;
    final DiaryRedisRepository drRepo;

    public DiaryDto(DiaryRepository dRepo, DiaryRedisRepository drRepo) {
        this.dRepo = dRepo;
        this.drRepo = drRepo;
    }

    // diaryRedisList - start
    public Map<ERest,Object> diaryRedisList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            Iterable<DiaryRedis> pageList = drRepo.findAll();
            Long totalcount = dRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Not Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Not Listeleme işlemi sırasında hata oluştu!");
        }
        return hm;
    }
    // diaryRedisList - end





    public Map<ERest,Object> DiaryList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<Diary> pageList = dRepo.findByOrderByDidAsc(pageable);
            Long totalcount = dRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Sayfalama işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Sayfalama işlemi sırasında hata oluştu!");
        }
        return hm;
    }

    public Map<ERest,Object> DiaryAdd( Diary diary){
        Map<ERest, Object> hm = new LinkedHashMap<>();

        try {
            Diary d = dRepo.save(diary);

            DiaryRedis dr = new DiaryRedis();
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, d );

            dr.setDid( UUID.randomUUID().toString() );
            dr.setTitle(d.getTitle());
            dr.setDetail(d.getDetail());
            dr.setDate(d.getDate());
            dr.setDtime(d.getDtime());

            drRepo.save(dr);


        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, " HATA! ->Bu Title (" + diary.getTitle()+") zaten var");
            }
        }
        return hm;
    }

    public Map<ERest, Object> updateDiary( Diary diary) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( diary.getDid() != null ){
            Optional<Diary> oDiary = dRepo.findById( diary.getDid());
            if( oDiary.isPresent() ) {
                try {
                    dRepo.saveAndFlush( diary );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme işlemi başarılı");
                    hm.put(ERest.result, diary);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    if (ex.toString().contains("constraint")) {
                        hm.put(ERest.message, " HATA! ->Bu Title (" + diary.getTitle()+") zaten var");
                    }
                }
            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, diary);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, diary);
        }
        return hm;
    }
    public Map<ERest, Object> deleteDiary( String stPid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            int pid = Integer.parseInt(stPid);
            dRepo.deleteById(pid);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Silme başarılı");
            hm.put(ERest.result, stPid);
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, stPid);
        }
        return hm;
    }
}
