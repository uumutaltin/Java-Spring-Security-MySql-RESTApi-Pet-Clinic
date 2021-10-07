package com.works.restcontrollers;

import com.works.dto.LabDto;
import com.works.entities.Category;
import com.works.entities.Lab;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/lab")
public class LabRestController {

    final LabDto lDto;
    final Util util;
    public LabRestController(LabDto lDto, Util util) {
        this.lDto = lDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  lDto.labList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Lab lab, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return lDto.labAdd(lab);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid Lab lab, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  lDto.updateLab(lab);
        }


    }

    @DeleteMapping("/delete/{lid}")
    public Map<ERest, Object> delete( @PathVariable String lid){
        return lDto.deleteLab(lid);
    }
}
