package com.works.dto;

import com.works.config.Config;
import com.works.entities.Bill;
import com.works.entities.Category;
import com.works.property.SaleProperties;
import com.works.repositories.BillRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BillDto {
    final BillRepository bRepo;

    public BillDto(BillRepository bRepo) {
        this.bRepo = bRepo;
    }

    public Map<ERest,Object> billList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<Bill> pageList = bRepo.findByOrderByDateAsc(pageable);
            Long totalcount = bRepo.count();
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

    public Map<ERest,Object> billAdd( Bill bill){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            Bill c = bRepo.save(bill);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, c );
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            hm.put(ERest.message,"Ekleme sırasında hata oluştu!");
        }
        return hm;
    }

    public Map<ERest, Object> deleteBill( String stCid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            bRepo.deleteById(stCid);
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

    public Map<ERest,Object> billSaleList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<SaleProperties> pageList = bRepo.saleList(pageable);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Alış-Satış Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Alış-Satış Listeleme işlemi sırasında hata oluştu!"+ ex);
        }
        return hm;
    }

    public Map<ERest,Object> billCashList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<SaleProperties> pageList = bRepo.cashList(pageable);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Nakit Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Nakit Listeleme işlemi sırasında hata oluştu!"+ ex);
        }
        return hm;
    }

    public Map<ERest,Object> billBankCardList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<SaleProperties> pageList = bRepo.bankCardList(pageable);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Banka Kartı Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Banka Kartı Listeleme işlemi sırasında hata oluştu!"+ ex);
        }
        return hm;
    }

    public Map<ERest,Object> billBankTransferList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<SaleProperties> pageList = bRepo.bankTransferList(pageable);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Banka Havale Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Banka Havale Listeleme işlemi sırasında hata oluştu!"+ ex);
        }
        return hm;
    }

    public Map<ERest,Object> billIncome(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<SaleProperties> pageList = bRepo.income(pageable);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Para Giriş Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Para Giriş Listeleme işlemi sırasında hata oluştu!"+ ex);
        }
        return hm;
    }

    public Map<ERest,Object> billExpense(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<SaleProperties> pageList = bRepo.expense(pageable);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Para Çıkış Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Para Çıkış Listeleme işlemi sırasında hata oluştu!"+ ex);
        }
        return hm;
    }
}
