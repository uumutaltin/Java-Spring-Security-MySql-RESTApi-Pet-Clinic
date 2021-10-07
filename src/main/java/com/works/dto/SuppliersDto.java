package com.works.dto;

import com.works.config.Config;
import com.works.entities.Suppliers;
import com.works.repositories.SuppliersRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SuppliersDto {

    final SuppliersRepository sRepo;

    public SuppliersDto(SuppliersRepository sRepo) {
        this.sRepo = sRepo;
    }

    public Map<ERest,Object> suppliersList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<Suppliers> pageList = sRepo.findByOrderBySidAsc(pageable);
            Long totalcount = sRepo.count();
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

    public Map<ERest,Object> suppliersAdd( Suppliers suppliers){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            Suppliers s = sRepo.save(suppliers);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, s );
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, " HATA! ->Bu Email (" + suppliers.getSemail()+") ile daha önce kayıt yapılmış");
            }
        }
        return hm;
    }

    public Map<ERest, Object> updateSupplier( Suppliers suppliers) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( suppliers.getSid() != null ){

            Optional<Suppliers> oSupplier = sRepo.findById( suppliers.getSid());
            if( oSupplier.isPresent() ) {

                try {
                    sRepo.saveAndFlush( suppliers );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme işlemi başarılı");
                    hm.put(ERest.result, suppliers);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    if (ex.toString().contains("constraint")) {
                        hm.put(ERest.message, "HATA! -> Bu email (" + suppliers.getSemail() + ") adresi ile daha önce kayıt yapılmış");
                    }
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, suppliers);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, suppliers);
        }


        return hm;

    }

    public Map<ERest, Object> deleteSuppliers( String stSid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            int cid = Integer.parseInt(stSid);
            sRepo.deleteById(cid);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Silme başarılı");
            hm.put(ERest.result, stSid);
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, stSid);
        }
        return hm;
    }
}
