package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@ApiModel(value = "Sepet Model",description = "Satış İşlemleri Sepet (Ekleme,Güncelleme,Silme) için Kullanılır.")
public class BoxAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boid; // box id

    @NotNull(message = "BoxAction bid NotNull")
    @NotEmpty(message = "BoxAction bid NotEmpty")
    private String bid; // bill id

    private Integer suid; // supplier id

    private Integer prodid; // product id

    private Integer quantity;

    private Integer price;

    private Integer warid;

}
