package com.works.dto;

import com.works.config.Config;
import com.works.property.IncomeByPaymentType;
import com.works.property.MostValCustomer;
import com.works.property.VisitCustomerPerWeek;
import com.works.repositories.BillRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticDto {
    final BillRepository bRepo;

    public StatisticDto(BillRepository bRepo) {
        this.bRepo = bRepo;
    }

    public Map<ERest,Object> mostValCus(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);

            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusDays(5);
            endDate = endDate.plusDays(1);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date end_date = Date.from( endDate.atStartOfDay(defaultZoneId).toInstant() );
            Date start_date = Date.from( startDate.atStartOfDay(defaultZoneId).toInstant() );
            List<MostValCustomer> pageList = bRepo.mostValueableCustomer(pageable,start_date,end_date);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "En Değerli Müşteri Sıralama işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"En Değerli Müşteri Sıralama işlemi sırasında hata oluştu!");
        }
        return hm;
    }

    public Map<ERest,Object> incomeByPaymentType(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<IncomeByPaymentType> pageList = bRepo.incomeByPaymentType(pageable);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Ödeme Tipine Göre Sıralama işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"EÖdeme Tipine Göre Sıralama işlemi sırasında hata oluştu!");
        }
        return hm;
    }

    public Map<ERest,Object> visitCountPerWeek(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusDays(5);
            endDate = endDate.plusDays(1);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date end_date = Date.from( endDate.atStartOfDay(defaultZoneId).toInstant() );
            Date start_date = Date.from( startDate.atStartOfDay(defaultZoneId).toInstant() );
            Integer visitCount = bRepo.visitCountPerWeek(start_date,end_date);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Son Bir Hafta Ziyaretçi Sayısı işlemi başarılı");
            hm.put(ERest.result, visitCount);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Son Bir Hafta Ziyaretçi Sayısı işlemi sırasında hata oluştu!");
        }
        return hm;
    }

    public Map<ERest,Object> visitCustomerPerWeek(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusDays(5);
            endDate = endDate.plusDays(1);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date end_date = Date.from( endDate.atStartOfDay(defaultZoneId).toInstant() );
            Date start_date = Date.from( startDate.atStartOfDay(defaultZoneId).toInstant() );
            List<VisitCustomerPerWeek> customer = bRepo.visitCustomerPerWeek(start_date,end_date);
            Long totalcount = bRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "En Çok İşlem Yapan Müşteri işlemi başarılı");
            hm.put(ERest.result, customer);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"En Çok İşlem Yapan Müşteri işlemi sırasında hata oluştu!");
        }
        return hm;
    }

}
