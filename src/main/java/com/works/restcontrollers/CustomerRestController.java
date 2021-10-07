package com.works.restcontrollers;


import com.works.documents.ElasticCustomer;
import com.works.dto.CustomerDto;
import com.works.entities.Customer;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    final CustomerDto cDto;
    final Util util;
    public CustomerRestController(CustomerDto cDto, Util util) {
        this.cDto = cDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  cDto.customerList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Customer customer, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return cDto.customerAdd(customer);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid Customer customer, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  cDto.updateCustomer(customer);
        }


    }

    @DeleteMapping("/delete/{cid}")
    public Map<ERest, Object> delete( @PathVariable String cid){
        return cDto.deleteCustomer(cid);
    }

    @GetMapping("/search/{p}/{data}")
    public Map<ERest,Object> search(@PathVariable String data,@PathVariable int p){

        return cDto.search(data, p);
    }




}
