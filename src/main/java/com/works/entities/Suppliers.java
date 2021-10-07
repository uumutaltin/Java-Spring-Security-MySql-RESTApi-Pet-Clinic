package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Data
@ApiModel(value = "Hayvan Model",description = "Yeni Tedarikçi Ekleme için Kullanılır.")
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;


    @NotNull(message = "Suppliers sname NotNull")
    @NotEmpty(message = "Suppliers sname NotEmpty")
    private String sname;

    @NotNull(message = "Suppliers semail NotNull")
    @NotEmpty(message = "Suppliers semail NotEmpty")
    private String semail;

    @NotNull(message = "Suppliers sphone NotNull")
    @NotEmpty(message = "Suppliers sphone NotEmpty")
    private String sphone;

    @NotNull(message = "Suppliers sstatus NotNull")
    @NotEmpty(message = "Suppliers sstatus NotEmpty")
    private String sstatus;

}
