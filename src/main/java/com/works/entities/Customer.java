package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@ApiModel(value = "Müşteri Model",description = "Yeni Müşteri Ekleme için Kullanılır.")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer cid;


    @NotNull(message = "Customer cname NotNull")
    @NotEmpty(message = "Customer cname NotEmpty")
    private String cname;

    @NotNull(message = "Customer csurname NotNull")
    @NotEmpty(message = "Customer csurname NotEmpty")
    private String csurname;

    @NotNull(message = "Customer mobile_phone NotNull")
    @NotEmpty(message = "Customer mobile_phone NotEmpty")
    private String mobile_phone;

    @NotNull(message = "Customer email NotNull")
    @NotEmpty(message = "Customer email NotEmpty")
    @Column(unique = true)
    private String  email;

    @NotNull(message = "Customer tax NotNull")
    private int tax;

    @NotNull(message = "Customer tax_administration NotNull")
    @NotEmpty(message = "Customer tax_administration NotEmpty")
    private String  tax_administration;

    @NotNull(message = "Customer ctype NotNull")
    private int ctype;

    @NotNull(message = "Customer cnote NotNull")
    @NotEmpty(message = "Customer cnote NotEmpty")
    private String cnote;

    @NotNull(message = "Customer cprovince NotNull")
    @NotEmpty(message = "Customer cprovince NotEmpty")
    private String cprovince;

    @NotNull(message = "Customer cdistrict NotNull")
    @NotEmpty(message = "Customer cdistrict NotEmpty")
    private String cdistrict;

    @NotNull(message = "Customer caddress NotNull")
    @NotEmpty(message = "Customer caddress NotEmpty")
    private String caddress;

    @NotNull(message = "Customer cdiscount NotNull")
    private int cdiscount;

    @OneToMany(cascade = CascadeType.REMOVE)
    @NotNull(message = "Customer pets NotNull")
    private List<Pet> pets;














}
