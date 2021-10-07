package com.works.dto;

import com.works.config.Config;
import com.works.entities.Product;
import com.works.entities.Suppliers;
import com.works.repositories.ProductRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductDto {

    final ProductRepository pRepo;

    public ProductDto(ProductRepository pRepo) {
        this.pRepo = pRepo;
    }

    public Map<ERest,Object> productList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<Product> pageList = pRepo.findByOrderByProidAsc(pageable);
            Long totalcount = pRepo.count();
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

    public Map<ERest,Object> productAdd( Product product){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            Product p = pRepo.save(product);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, p );
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, " HATA! ->Bu Barcode (" + product.getProductbarcode()+") ait ürün zaten var");
                hm.put(ERest.message, " HATA! ->Bu Code (" + product.getProductcode()+") ait ürün zaten var");
                hm.put(ERest.message, " HATA! ->Bu name (" + product.getProductname()+") ait ürün zaten var");
            }
        }
        return hm;
    }

    public Map<ERest, Object> updateProduct( Product product) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( product.getProid() != null ){

            Optional<Product> oProduct = pRepo.findById( product.getProid());
            if( oProduct.isPresent() ) {

                try {
                    pRepo.saveAndFlush( product );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme işlemi başarılı");
                    hm.put(ERest.result, product);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    if (ex.toString().contains("constraint")) {
                        hm.put(ERest.message, " HATA! ->Bu Barcode (" + product.getProductbarcode()+") ait ürün zaten var");
                        hm.put(ERest.message, " HATA! ->Bu Barcode (" + product.getProductcode()+") ait ürün zaten var");
                        hm.put(ERest.message, " HATA! ->Bu Barcode (" + product.getProductname()+") ait ürün zaten var");
                    }
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, product);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, product);
        }


        return hm;

    }

    public Map<ERest, Object> deleteProduct( String stPid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            int pid = Integer.parseInt(stPid);
            pRepo.deleteById(pid);
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
