package com.works.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@ApiModel(value = "Ürün Stok Model",description = "Stok (Ekleme,Güncelleme,Silme) için Kullanılır.")
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer psid;

    @NotNull(message = "Category name NotNull")
    private Integer prodid;

    @NotNull(message = "Category name NotNull")
    private Integer waid;

    @NotNull(message = "Category name NotNull")
    private  Integer stock;


    @JsonFormat(pattern="dd.MM.yyyy HH:mm")
    @Column(name = "date")
    private Date date = new Date();




}
