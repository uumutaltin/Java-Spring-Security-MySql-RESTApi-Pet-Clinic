package com.works.restcontrollers;

import com.works.dto.BillDto;
import com.works.entities.Bill;
import com.works.entities.Category;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/bill")
public class BillRestController {

    final BillDto bDto;
    final Util util;
    public BillRestController(BillDto bDto, Util util) {
        this.bDto = bDto;
        this.util = util;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  bDto.billList(pageNo);
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Bill bill, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return bDto.billAdd(bill);
        }
    }

    @DeleteMapping("/delete/{bid}")
    public Map<ERest, Object> delete( @PathVariable String bid){
        return bDto.deleteBill(bid);
    }

    @GetMapping("sale/list/{pageNo}")
    public Map<ERest, Object> saleList(@PathVariable String pageNo){
        return  bDto.billSaleList(pageNo);
    }

    @GetMapping("sale/cashlist/{pageNo}")
    public Map<ERest, Object> cashList(@PathVariable String pageNo){
        return  bDto.billCashList(pageNo);
    }

    @GetMapping("sale/bankcardlist/{pageNo}")
    public Map<ERest, Object> bankCardList(@PathVariable String pageNo){
        return  bDto.billBankCardList(pageNo);
    }

    @GetMapping("sale/banktransferlist/{pageNo}")
    public Map<ERest, Object> bankTransferList(@PathVariable String pageNo){
        return  bDto.billBankTransferList(pageNo);
    }

    @GetMapping("sale/income/{pageNo}")
    public Map<ERest, Object> incomeList(@PathVariable String pageNo){
        return  bDto.billIncome(pageNo);
    }

    @GetMapping("sale/expense/{pageNo}")
    public Map<ERest, Object> expenseList(@PathVariable String pageNo){
        return  bDto.billExpense(pageNo);
    }

}
