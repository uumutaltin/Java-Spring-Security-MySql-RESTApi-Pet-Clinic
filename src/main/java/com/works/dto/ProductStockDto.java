package com.works.dto;

import com.works.config.Config;
import com.works.entities.Product;
import com.works.entities.ProductStock;
import com.works.repositories.ProductStockRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductStockDto {

    final ProductStockRepository psRepo;

    public ProductStockDto(ProductStockRepository psRepo) {
        this.psRepo = psRepo;
    }

    // productStockList - start
    public Map<ERest,Object> productStockList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<ProductStock> pageList = psRepo.findByOrderByPsidAsc(pageable);
            Long totalcount = psRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Ürün Stok Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Ürün Stok Listeleme işlemi sırasında hata oluştu!");
        }
        return hm;
    }
    // productStockList - end

    // productStockAdd - start
    public Map<ERest,Object> productStockAdd( ProductStock productStock){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            List<ProductStock> ls =psRepo.findByProdidEqualsAndWaidEqualsOrderByPsidAsc(productStock.getProdid(),productStock.getWaid());
            if (ls.isEmpty()){
                ProductStock proStock = psRepo.save(productStock);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ürün Stok Ekleme başarılı");
                hm.put(ERest.result, proStock );
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Bu Ürün Bu Depoya ile daha önce eklenmiş ");
            }

        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, "Bu Ürün Bu Depoya ile daha önce eklenmiş ");
            }
        }
        return hm;
    }
    // productStockAdd - end

    // updateProductStock - start
    public Map<ERest, Object> updateProductStock( ProductStock productStock) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( productStock.getPsid() != null ){

            Optional<ProductStock> oProductStock = psRepo.findById( productStock.getPsid());
            if( oProductStock.isPresent() ) {

                try {
                    List<ProductStock> ls =psRepo.findByProdidEqualsAndWaidEqualsOrderByPsidAsc(productStock.getProdid(),productStock.getWaid());
                    System.out.println(ls);
                    if (ls.isEmpty() || ls.get(0).getPsid()== productStock.getPsid()){
                        psRepo.saveAndFlush( productStock );
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Ürün Güncelleme başarılı");
                        hm.put(ERest.result, productStock);
                    }else{
                        hm.put(ERest.status, false);
                        hm.put(ERest.message, "Bu Ürün Bu Depoya ile daha önce eklenmiş ");
                    }

                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    if (ex.toString().contains("constraint")) {
                        hm.put(ERest.message, "Bu Ürün Bu Depoya ile daha önce eklenmiş ");
                    }
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Ürün Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, productStock);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Ürün Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, productStock);
        }


        return hm;

    }
    // updateProductStock - end

    // deleteProductStock - start
    public Map<ERest, Object> deleteProductStock( String stPsid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            int psid = Integer.parseInt(stPsid);
            psRepo.deleteById(psid);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ürün Stok Silme başarılı");
            hm.put(ERest.result, stPsid);
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Ürün Stok Silme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, stPsid);
        }
        return hm;
    }
}
