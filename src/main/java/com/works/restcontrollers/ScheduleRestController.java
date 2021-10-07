package com.works.restcontrollers;

import com.works.dto.ScheduleCalendarDto;
import com.works.entities.Customer;
import com.works.entities.ScheduleCalendar;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleRestController {

    final ScheduleCalendarDto scDto;
    final Util util;
    public ScheduleRestController(ScheduleCalendarDto scDto, Util util) {
        this.scDto = scDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  scDto.ScheduleList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid ScheduleCalendar scheduleCalendar, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return scDto.ScheduleAdd(scheduleCalendar);
        }
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid ScheduleCalendar scheduleCalendar, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  scDto.updateSchedule(scheduleCalendar);
        }
    }

    @DeleteMapping("/delete/{cid}")
    public Map<ERest, Object> delete( @PathVariable String cid){
        return scDto.deleteSchedule(cid);
    }

}
