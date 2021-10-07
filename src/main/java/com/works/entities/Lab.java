package com.works.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@ApiModel(value = "Laboratuvar Model",description = "Yeni Laboratuvar Verisi Ekleme için Kullanılır.")
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lid", nullable = false)
    private Integer lid;

    @NotNull(message = "Lab type NotNull")
    private int type;

    @NotNull(message = "Lab result NotNull")
    @NotEmpty(message = "Lab result NotEmpty")
    private String result;


    @OneToOne(cascade = CascadeType.REMOVE)
    @NotNull(message = "Lab labimage NotNull")
    private Image labimage;


    @OneToOne
    @NotNull(message = "Lab pet NotNull")
    private Pet pet;

    @JsonFormat(pattern="dd.MM.yyyy HH:mm")
    @NotNull(message = "Lab date NotNull")
    private Date date = new Date();


}
