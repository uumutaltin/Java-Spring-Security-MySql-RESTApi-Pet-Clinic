package com.works.restcontrollers;

import com.works.dto.ProductDto;
import com.works.entities.Product;
import com.works.entities.Suppliers;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    final ProductDto pDto;
    final Util util;
    public ProductRestController(ProductDto pDto, Util util) {
        this.pDto = pDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  pDto.productList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Product product, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return pDto.productAdd(product);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid Product product, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  pDto.updateProduct(product);
        }


    }

    @DeleteMapping("/delete/{pid}")
    public Map<ERest, Object> delete( @PathVariable String pid){
        return pDto.deleteProduct(pid);
    }
}
