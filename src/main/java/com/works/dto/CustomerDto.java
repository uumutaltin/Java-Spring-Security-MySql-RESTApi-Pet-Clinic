package com.works.dto;


import com.works.config.Config;
import com.works.documents.ElasticCustomer;
import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.repositories.elastic.ElasticSearchRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerDto {

    final CustomerRepository cRepo;
    final ElasticSearchRepository eRepo;


    public CustomerDto(CustomerRepository cRepo, ElasticSearchRepository eRepo) {
        this.cRepo = cRepo;
        this.eRepo = eRepo;

    }

    // customerList - start
    public Map<ERest,Object> customerList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<Customer> pageList = cRepo.findByOrderByCidAsc(pageable);
            List<Customer> ls =cRepo.findAll();
            Long totalcount = cRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Sayfalama işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
            eRepo.deleteAll();
            ls.forEach(item->{
                ElasticCustomer elasticCustomer = new ElasticCustomer();
                elasticCustomer.setCid(item.getCid());
                elasticCustomer.setCname(item.getCname());
                elasticCustomer.setCsurname(item.getCsurname());
                elasticCustomer.setMobile_phone(item.getMobile_phone());
                elasticCustomer.setEmail(item.getEmail());
                elasticCustomer.setTax(item.getTax());
                elasticCustomer.setTax_administration(item.getTax_administration());
                elasticCustomer.setCtype(item.getCtype());
                elasticCustomer.setCnote(item.getCnote());
                elasticCustomer.setCprovince(item.getCprovince());
                elasticCustomer.setCdistrict(item.getCdistrict());
                elasticCustomer.setCaddress(item.getCaddress());
                elasticCustomer.setCdiscount(item.getCdiscount());
                eRepo.save(elasticCustomer);

            });



        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Sayfalama işlemi sırasında hata oluştu!");
        }
        return hm;
    }
    // customerList - end

    // customerAdd - start
    public Map<ERest,Object> customerAdd( Customer customer){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            Customer c = cRepo.save(customer);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Ekleme başarılı");
            hm.put(ERest.result, c );
        }catch (Exception ex) {
            hm.put(ERest.status, false);
            if (ex.toString().contains("constraint")) {
                hm.put(ERest.message, "Bu email (" + customer.getEmail() + ") ile daha önce kayıt yapılmış");
            }
        }
        return hm;
    }
    // customerAdd - end

    // updateCustomer - start
    public Map<ERest, Object> updateCustomer( Customer customer) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if( customer.getCid() != null ){

            Optional<Customer> oCustomer = cRepo.findById( customer.getCid());
            if( oCustomer.isPresent() ) {

                try {
                    cRepo.saveAndFlush( customer );
                    hm.put(ERest.status, true);
                    hm.put(ERest.message, "Güncelleme başarılı");
                    hm.put(ERest.result, customer);
                }catch (Exception ex){
                    hm.put(ERest.status, false);
                    if (ex.toString().contains("constraint")) {
                        hm.put(ERest.message, "Bu email (" + customer.getEmail() + ") adresi ile daha önce kayıt yapılmış");
                    }
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
                hm.put(ERest.result, customer);
            }
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Güncelleme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, customer);
        }


        return hm;

    }
    // updateCustomer - end

    // deleteCustomer - start
    public Map<ERest, Object> deleteCustomer( String stCid ) {
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
    // deleteCustomer - end

    public Map<ERest,Object> search(@PathVariable String data, @PathVariable int p){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        Page<ElasticCustomer> pages = eRepo.findBySearch(data, PageRequest.of(p,10));
        hm.put(ERest.totalSize, pages.getTotalPages());
        hm.put(ERest.result,pages.getContent());
        return hm;
    }





}
