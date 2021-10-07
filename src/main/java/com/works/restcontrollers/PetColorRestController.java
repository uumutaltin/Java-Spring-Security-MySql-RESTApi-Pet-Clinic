package com.works.restcontrollers;

import com.works.dto.PetDto;
import com.works.entities.PetColor;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/petColor")
public class PetColorRestController {

    final PetDto pDto;
    final Util util;
    public PetColorRestController(PetDto pDto, Util util) {
        this.pDto = pDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest,Object> list(@PathVariable String pageNo ){
        return pDto.petColorList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest,Object> add(@RequestBody @Valid PetColor petColor, BindingResult bindingResult){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        if(bindingResult.hasErrors()){
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult) );
            return hm;
        }else {
            return pDto.petColorAdd(petColor);
        }




    }

}
