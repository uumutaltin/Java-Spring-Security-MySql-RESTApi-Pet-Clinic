package com.works.dto;

import com.works.config.Config;
import com.works.entities.BoxAction;
import com.works.entities.Product;
import com.works.repositories.BoxRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoxActionDto {

    final BoxRepository boRepo;
    public BoxActionDto(BoxRepository boRepo) {
        this.boRepo = boRepo;
    }

    // boxList - start
    public Map<ERest,Object> boxList(String pageNumber,Integer suid){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<BoxAction> pageList = boRepo.findBySuidEqualsAllIgnoreCase(suid,pageable);
            Long totalcount = boRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Sepet Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Sepet Listeleme işlemi sırasında hata oluştu!");
        }
        return hm;
    }
    // boxList - end

    // boxAdd - start
    public Map<ERest,Object> boxAdd( BoxAction boxAction){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            BoxAction ba = boRepo.save(boxAction);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Sepete Ekleme başarılı");
            hm.put(ERest.result, ba );
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, "Bu bilgiler ile daha önce kayıt yapılmış");
            }
        }
        return hm;
    }
    // boxAdd - end

    // deleteProduct - start
    public Map<ERest, Object> deleteBox( String stBoid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            int boxid = Integer.parseInt(stBoid);
            boRepo.deleteById(boxid);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Sepetten Silme başarılı");
            hm.put(ERest.result, stBoid);
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Sepetten Silme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, stBoid);
        }
        return hm;
    }
    // deleteProduct - end
}
