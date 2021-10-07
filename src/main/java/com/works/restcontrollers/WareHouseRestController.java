package com.works.restcontrollers;

import com.works.dto.WareHouseDto;
import com.works.entities.Suppliers;
import com.works.entities.WareHouse;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WareHouseRestController {

    final WareHouseDto wDto;
    final Util util;
    public WareHouseRestController(WareHouseDto wDto, Util util) {
        this.wDto = wDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  wDto.WareHouseList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid WareHouse wareHouse, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return wDto.wareHouseAdd(wareHouse);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid WareHouse wareHouse, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  wDto.updateWareHouse(wareHouse);
        }


    }

    @DeleteMapping("/delete/{wid}")
    public Map<ERest, Object> delete( @PathVariable String wid){
        return wDto.deleteWareHouese(wid);
    }


}
