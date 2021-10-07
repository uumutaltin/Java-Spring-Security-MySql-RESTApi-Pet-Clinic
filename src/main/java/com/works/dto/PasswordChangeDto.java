package com.works.dto;

import com.works.entities.Users;
import com.works.property.PasswordChangeProperties;
import com.works.repositories.UserRepository;
import com.works.services.UserService;
import com.works.utils.ERest;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PasswordChangeDto {
    final UserService uService;
    final UserRepository uRepo;

    public PasswordChangeDto(UserService uService, UserRepository uRepo) {
        this.uService = uService;
        this.uRepo = uRepo;
    }

    public Map<ERest, Object> passwordChange(PasswordChangeProperties passwordChangeProperties){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        String uPassword = uService.encoder().encode(passwordChangeProperties.getPassword());
        passwordChangeProperties.setPassword(uPassword);
        Users user = new Users();
        user= uRepo.findById(passwordChangeProperties.getId()).get();
        user.setPassword(uPassword);
        Users userx = new Users();
        userx=uRepo.saveAndFlush(user);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Şifre Degiştirme Başarılı");
        hm.put(ERest.result, userx);

        return hm;
    }
}
