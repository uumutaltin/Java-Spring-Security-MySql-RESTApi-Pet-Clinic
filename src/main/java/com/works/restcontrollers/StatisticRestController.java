package com.works.restcontrollers;

import com.works.dto.StatisticDto;
import com.works.utils.ERest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistic")
public class StatisticRestController {
    final StatisticDto sDto;

    public StatisticRestController(StatisticDto sDto) {
        this.sDto = sDto;
    }

    @GetMapping("mostvalcus/{pageNo}")
    public Map<ERest, Object> mostValCusList(@PathVariable String pageNo){
        return  sDto.mostValCus(pageNo);
    }

    @GetMapping("paymenttype/{pageNo}")
    public Map<ERest, Object> incomeByPaymentList(@PathVariable String pageNo){
        return  sDto.incomeByPaymentType(pageNo);
    }

    @GetMapping("visitcount")
    public Map<ERest, Object> visitCountPerWeek(){
        return  sDto.visitCountPerWeek();
    }

    @GetMapping("mostvisitedcustomer")
    public Map<ERest, Object> visitCustomerPerWeek(){
        return  sDto.visitCustomerPerWeek();
    }

}
