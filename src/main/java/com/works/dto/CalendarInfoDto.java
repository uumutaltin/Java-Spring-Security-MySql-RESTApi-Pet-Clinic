package com.works.dto;

import com.works.config.Config;
import com.works.entities.CalendarInfo;
import com.works.entities.Category;
import com.works.repositories.CalendarInfoRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CalendarInfoDto {

    final CalendarInfoRepository cRepo;
    public CalendarInfoDto(CalendarInfoRepository cRepo) {
        this.cRepo = cRepo;
    }

    public Map<ERest,Object> calendarInfoList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<CalendarInfo> pageList = cRepo.findByOrderByCidAsc(pageable);
            Long totalcount = cRepo.count();
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

    public Map<ERest,Object> calendarInfoAdd( CalendarInfo calendarInfo){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            CalendarInfo c = cRepo.save(calendarInfo);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, c );
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, " HATA! ->Bu Calendar Başlıgı (" + calendarInfo.getCname()+") zaten var!");
            }
        }
        return hm;
    }

    public Map<ERest, Object> updatecalendarInfo( CalendarInfo calendarInfo) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( calendarInfo.getCid() != null ){

            Optional<CalendarInfo> oCalendarInfo = cRepo.findById( calendarInfo.getCid());
            if( oCalendarInfo.isPresent() ) {

                try {
                    cRepo.saveAndFlush( calendarInfo );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme işlemi başarılı");
                    hm.put(ERest.result, calendarInfo);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    if (ex.toString().contains("constraint")) {
                        hm.put(ERest.message, " HATA! ->Bu Calendar Başlıgı (" + calendarInfo.getCname()+") zaten var!");
                    }
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, calendarInfo);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, calendarInfo);
        }


        return hm;

    }

    public Map<ERest, Object> deletecalendarInfo( String stCid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            int cid = Integer.parseInt(stCid);
            cRepo.deleteById(cid);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Silme başarılı");
            hm.put(ERest.result, stCid);
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, stCid);
        }
        return hm;
    }
}
