package com.works.restcontrollers;

import com.works.dto.SuppliersDto;
import com.works.entities.Customer;
import com.works.entities.Suppliers;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/suppliers")
public class SuppliersRestController {

    final SuppliersDto sDto;
    final Util util;
    public SuppliersRestController(SuppliersDto sDto, Util util) {
        this.sDto = sDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  sDto.suppliersList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Suppliers suppliers, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return sDto.suppliersAdd(suppliers);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid Suppliers suppliers, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  sDto.updateSupplier(suppliers);
        }


    }

    @DeleteMapping("/delete/{sid}")
    public Map<ERest, Object> delete( @PathVariable String sid){
        return sDto.deleteSuppliers(sid);
    }
}
