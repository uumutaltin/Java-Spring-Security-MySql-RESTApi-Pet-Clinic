package com.works.dto;

import com.works.config.Config;
import com.works.entities.Category;
import com.works.entities.Suppliers;
import com.works.repositories.CategoryRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class CategoryDto {

    final CategoryRepository cRepo;

    public CategoryDto(CategoryRepository cRepo) {
        this.cRepo = cRepo;
    }


    public Map<ERest,Object> categoryList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<Category> pageList = cRepo.findByOrderByCaidAsc(pageable);
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

    public Map<ERest,Object> categoryAdd( Category category){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            Category c = cRepo.save(category);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, c );
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, " HATA! ->Bu Kategori (" + category.getCategoryname()+") zaten var!");
            }
        }
        return hm;
    }

    public Map<ERest, Object> updateCategory( Category category) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( category.getCaid() != null ){

            Optional<Category> oCategory = cRepo.findById( category.getCaid());
            if( oCategory.isPresent() ) {

                try {
                    cRepo.saveAndFlush( category );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme işlemi başarılı");
                    hm.put(ERest.result, category);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    if (ex.toString().contains("constraint")) {
                        hm.put(ERest.message, "HATA! -> Bu Kategori (" + category.getCategoryname() + ") zaten var");
                    }
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, category);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, category);
        }


        return hm;

    }

    public Map<ERest, Object> deleteCategory( String stCid ) {
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
