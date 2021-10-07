package com.works.restcontrollers;


import com.works.dto.PasswordChangeDto;
import com.works.property.PasswordChangeProperties;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/password")
public class PasswordChangeRestController {

    final PasswordChangeDto pDto;
    final Util util;
    public PasswordChangeRestController(PasswordChangeDto pDto, Util util) {
        this.pDto = pDto;
        this.util = util;
    }

    @PutMapping("/change")
    public Map<ERest, Object> update(@RequestBody @Valid PasswordChangeProperties pcp, BindingResult bindingResult){

        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            hm.put(ERest.status, false);
            hm.put(ERest.errors, util.errors(bindingResult));
            return hm;
        } else {
            return  pDto.passwordChange(pcp);
        }


    }

}
