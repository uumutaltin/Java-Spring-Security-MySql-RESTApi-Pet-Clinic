package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@ApiModel(value = "Ürün Model",description = "Yeni Ürün Ekleme için Kullanılır.")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proid", nullable = false)
    private Integer proid;

    @Column(unique = true)
    @ApiModelProperty(value = "Product Name", required = true, notes = "Product Name Belirtilmediğinde Product Servisi Sata Verir!")
    @NotNull(message = "Product productname NotNull")
    @NotEmpty(message = "Product productname NotEmpty")
    private String productname;

    @NotNull(message = "Product productunit NotNull")
    private Integer productunit;

    @NotNull(message = "Product producttype NotNull")
    private Integer producttype;

    @Column(unique = true)
    @ApiModelProperty(value = "Product Barcode", required = true, notes = "Product Barcode Belirtilmediğinde Product Servisi Sata Verir!")
    @NotNull(message = "Product productbarcode NotNull")
    @NotEmpty(message = "Product productbarcode NotEmpty")
    private String productbarcode;

    @Column(unique = true)
    @ApiModelProperty(value = "Product Code", required = true, notes = "Product Code Belirtilmediğinde Product Servisi Sata Verir!")
    @NotNull(message = "Product productcode NotNull")
    @NotEmpty(message = "Product productcode NotEmpty")
    private String productcode;


    @NotNull(message = "Product producttax NotNull")
    private Integer producttax;

    @NotNull(message = "Product buyprice NotNull")
    private Integer buyprice;

    @NotNull(message = "Product sellprice NotNull")
    private Integer sellprice;

    @NotNull(message = "Product criticalquantity NotNull")
    private Integer criticalquantity;


    @NotNull(message = "Product productstatus NotNull")
    @NotEmpty(message = "Product productstatus NotEmpty")
    private String productstatus;


    @NotNull(message = "Product pspki NotNull")
    @NotEmpty(message = "Product pspki NotEmpty")
    private String pspki;

    @NotNull(message = "Product pbpki NotNull")
    @NotEmpty(message = "Product pbpki NotEmpty")
    private String pbpki;

    @OneToOne
    @NotNull(message = "Product productsuppliers NotNull")
    private Suppliers productsuppliers;

    @OneToOne
    @NotNull(message = "Product productcategory NotNull")
    private Category productcategory;



}

