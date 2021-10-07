package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Hayvan Irk Model",description = "Yeni Hayvan Irkı Ekleme için Kullanılır.")
public class PetRace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid", nullable = false)
    private Integer rid;

    @ApiModelProperty(value = "Hayvan Irk", required = true, notes = "Irk belirtilmediğinde Pet servisi hata verir!")
    @NotNull(message = "PetRace prace NotNull")
    @NotEmpty(message = "PetRace prace NotEmpty")
    private String prace;


}
