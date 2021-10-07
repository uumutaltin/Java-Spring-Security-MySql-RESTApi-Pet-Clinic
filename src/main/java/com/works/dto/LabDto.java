package com.works.dto;

import com.works.config.Config;
import com.works.entities.Category;
import com.works.entities.Lab;
import com.works.repositories.LabRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LabDto {

    final LabRepository lRepo;

    public LabDto(LabRepository lRepo) {
        this.lRepo = lRepo;
    }

    public Map<ERest,Object> labList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<Lab> pageList = lRepo.findByOrderByLidAsc(pageable);
            Long totalcount = lRepo.count();
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

    public Map<ERest,Object> labAdd( Lab lab){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            Lab l = lRepo.save(lab);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, l );
        }catch (Exception ex) {
            hm.put(ERest.status, false);

            hm.put(ERest.message, " Ekleme sırasında hata oluştu");

        }
        return hm;
    }


    public Map<ERest, Object> updateLab( Lab lab) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( lab.getLid() != null ){

            Optional<Lab> oLab = lRepo.findById( lab.getLid());
            if( oLab.isPresent() ) {

                try {
                    lRepo.saveAndFlush( lab );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme işlemi başarılı");
                    hm.put(ERest.result, lab);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    hm.put(ERest.message, " Ekleme sırasında hata oluştu");
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, lab);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, lab);
        }


        return hm;

    }

    public Map<ERest, Object> deleteLab( String stLid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            int lid = Integer.parseInt(stLid);
            lRepo.deleteById(lid);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Silme başarılı");
            hm.put(ERest.result, stLid);
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, stLid);
        }
        return hm;
    }
}
