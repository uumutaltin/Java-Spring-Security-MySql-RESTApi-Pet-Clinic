package com.works.property;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Şifre Değiştir Model")
public class PasswordChangeProperties {

    private int id;

    private String password;
    private String repassword;

}
