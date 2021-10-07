package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Hayvan Model",description = "Yeni Hayvan Ekleme için Kullanılır.\nckind = 1 - Kedi\nckind = 2 - Köpek\nckind = 3 - Kanatlı\nckind = 4 - Büyükbaş\nckind = 5 - Egzotik\nckind = 6 - Diğer")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", nullable = false)
    private Integer pid;


    @NotNull(message = "Pet cpatient NotNull")
    @NotEmpty(message = "Pet cpatient NotEmpty")
    private String cpatient;

    @NotNull(message = "Pet cchip NotNull")
    @Column(unique = true)
    private int cchip;

    @NotNull(message = "Pet creport NotNull")
    private int creport;

    @NotNull(message = "Pet cbirth NotNull")
    @NotEmpty(message = "Pet cbirth NotEmpty")
    private String cbirth;

    @NotNull(message = "Pet ckind NotNull")
    private int ckind;

    @NotNull(message = "Pet cgender NotNull")
    @NotEmpty(message = "Pet cgender NotEmpty")
    private String cgender;

    @NotNull(message = "Pet cbarren NotNull")
    @NotEmpty(message = "Pet cbarren NotEmpty")
    private String cbarren;

    @OneToOne
    @NotNull(message = "Pet pColor NotNull")
    private PetColor pColor;

    @OneToOne
    @NotNull(message = "Pet pRace NotNull")
    private PetRace pRace;


}
