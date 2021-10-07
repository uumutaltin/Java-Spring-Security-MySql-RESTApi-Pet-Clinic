package com.works.dto;

import com.works.config.Config;
import com.works.entities.Diary;
import com.works.entities.ScheduleCalendar;
import com.works.repositories.ScheduleCalendarRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ScheduleCalendarDto {
    final ScheduleCalendarRepository scRepo;

    public ScheduleCalendarDto(ScheduleCalendarRepository scRepo) {
        this.scRepo = scRepo;
    }

    public Map<ERest,Object> ScheduleList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<ScheduleCalendar> pageList = scRepo.findByOrderBySidAsc(pageable);
            Long totalcount = scRepo.count();
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

    public Map<ERest,Object> ScheduleAdd( ScheduleCalendar scheduleCalendar){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            ScheduleCalendar d = scRepo.save(scheduleCalendar);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, d );
        }catch (Exception ex) {
            hm.put(ERest.status, false);

        }
        return hm;
    }

    public Map<ERest, Object> updateSchedule( ScheduleCalendar scheduleCalendar) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( scheduleCalendar.getSid() != null ){

            Optional<ScheduleCalendar> oSchedule = scRepo.findById( scheduleCalendar.getSid());
            if( oSchedule.isPresent() ) {

                try {
                    scRepo.saveAndFlush( scheduleCalendar );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme işlemi başarılı");
                    hm.put(ERest.result, scheduleCalendar);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, scheduleCalendar);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, scheduleCalendar);
        }
        return hm;
    }

    public Map<ERest, Object> deleteSchedule( String stPid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            int pid = Integer.parseInt(stPid);
            scRepo.deleteById(pid);
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
