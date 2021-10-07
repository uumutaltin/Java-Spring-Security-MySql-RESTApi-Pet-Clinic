package com.works.restcontrollers;

import com.works.dto.BoxActionDto;
import com.works.entities.BoxAction;
import com.works.entities.Category;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/box")
public class BoxActionRestController {

    final BoxActionDto boDto;
    final Util util ;
    public BoxActionRestController(BoxActionDto boDto, Util util) {
        this.boDto = boDto;
        this.util = util;
    }

    @GetMapping("/list/{suid}/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo,@PathVariable Integer suid){
        return  boDto.boxList(pageNo,suid);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid BoxAction boxAction, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return boDto.boxAdd(boxAction);
        }
    }

    @DeleteMapping("/delete/{boid}")
    public Map<ERest, Object> delete( @PathVariable String boid){
        return boDto.deleteBox(boid);
    }

}
