package com.works.restcontrollers;

import com.works.dto.CalendarInfoDto;
import com.works.entities.CalendarInfo;
import com.works.entities.Category;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/calendarinfo")
public class CalendarInfoRestController {

    final CalendarInfoDto cDto;
    final Util util;
    public CalendarInfoRestController(CalendarInfoDto cDto, Util util) {
        this.cDto = cDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  cDto.calendarInfoList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid CalendarInfo calendarInfo, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return cDto.calendarInfoAdd(calendarInfo);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid CalendarInfo calendarInfo, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  cDto.updatecalendarInfo(calendarInfo);
        }


    }

    @DeleteMapping("/delete/{cid}")
    public Map<ERest, Object> delete( @PathVariable String cid){
        return cDto.deletecalendarInfo(cid);
    }


}
