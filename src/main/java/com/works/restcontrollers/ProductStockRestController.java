package com.works.restcontrollers;

import com.works.dto.ProductStockDto;
import com.works.entities.Customer;
import com.works.entities.ProductStock;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/productstock")
public class ProductStockRestController {

    final ProductStockDto psDto;
    final Util util ;
    public ProductStockRestController(ProductStockDto psDto, Util util) {
        this.psDto = psDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  psDto.productStockList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid ProductStock productStock, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return psDto.productStockAdd(productStock);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid ProductStock productStock, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  psDto.updateProductStock(productStock);
        }
    }

    @DeleteMapping("/delete/{psid}")
    public Map<ERest, Object> delete( @PathVariable String psid){
        return psDto.deleteProductStock(psid);
    }
}
