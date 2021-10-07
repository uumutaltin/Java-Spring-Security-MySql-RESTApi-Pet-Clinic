package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Hayvan Renk Model",description = "Yeni Hayvan Rengi Ekleme için Kullanılır.")
public class PetColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcid", nullable = false)
    private Integer pcid;

    @ApiModelProperty(value = "Hayvan Renk", required = true)
    @NotNull(message = "PetColor pcolor NotNull")
    @NotEmpty(message = "PetColor pcolor NotEmpty")
    private String pcolor;


}
