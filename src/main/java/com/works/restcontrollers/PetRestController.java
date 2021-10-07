package com.works.restcontrollers;

import com.works.dto.PetDto;
import com.works.entities.Pet;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/pet")
public class PetRestController {

    final PetDto pDto;
    final Util util;
    public PetRestController(PetDto pDto, Util util) {
        this.pDto = pDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo) {
        return pDto.petList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Pet pet, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return pDto.petAdd(pet);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid Pet pet, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  pDto.updatePet(pet);
        }



    }

}
