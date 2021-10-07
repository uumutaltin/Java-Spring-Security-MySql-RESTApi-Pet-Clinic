package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@ApiModel(value = "Depo Model",description = "Yeni Depo Ekleme için Kullanılır.")
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wid;

    @Column(unique = true)

    @NotNull(message = "WareHouse wname NotNull")
    @NotEmpty(message = "WareHouse wname NotEmpty")
    private String wname;
}

