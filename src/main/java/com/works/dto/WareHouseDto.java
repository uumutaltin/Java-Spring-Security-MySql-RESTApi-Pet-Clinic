package com.works.dto;

import com.works.config.Config;
import com.works.entities.Suppliers;
import com.works.entities.WareHouse;
import com.works.repositories.WareHouseRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WareHouseDto {
    final WareHouseRepository wRepo;

    public WareHouseDto(WareHouseRepository wRepo) {
        this.wRepo = wRepo;
    }

    public Map<ERest,Object> WareHouseList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<WareHouse> pageList = wRepo.findByOrderByWidAsc(pageable);
            Long totalcount = wRepo.count();
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

    public Map<ERest,Object> wareHouseAdd( WareHouse wareHouse){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            WareHouse w = wRepo.save(wareHouse);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, w );
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, " HATA! ->Bu Depo ismi (" + wareHouse.getWname()+") ile daha önce kayıt yapılmış");
            }
        }
        return hm;
    }

    public Map<ERest, Object> updateWareHouse( WareHouse wareHouse) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( wareHouse.getWid() != null ){

            Optional<WareHouse> oWare = wRepo.findById( wareHouse.getWid());
            if( oWare.isPresent() ) {

                try {
                    wRepo.saveAndFlush( wareHouse );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme işlemi başarılı");
                    hm.put(ERest.result, wareHouse);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    if (ex.toString().contains("constraint")) {
                        hm.put(ERest.message, " HATA! ->Bu Depo ismi (" + wareHouse.getWname() + ") ile daha önce kayıt yapılmış");
                    }
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, wareHouse);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, wareHouse);
        }
        return hm;

    }

    public Map<ERest, Object> deleteWareHouese( String stWid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            int wid = Integer.parseInt(stWid);
            wRepo.deleteById(wid);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Silme başarılı");
            hm.put(ERest.result, stWid);
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, stWid);
        }
        return hm;
    }
}
