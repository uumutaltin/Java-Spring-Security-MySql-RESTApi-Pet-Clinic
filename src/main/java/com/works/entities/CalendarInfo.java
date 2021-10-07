package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Takvim Model",description = "Yeni Takvim Başlıgı Ekleme için Kullanılır.")
public class CalendarInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer cid;

    @NotNull(message = "Calendar cname NotNull")
    @NotEmpty(message = "Calendar cname NotEmpty")
    private String cname;

    @NotNull(message = "Calendar ccolor NotNull")
    @NotEmpty(message = "Calendar ccolor NotEmpty")
    private String ccolor;

    @NotNull(message = "Calendar cbgColor NotNull")
    @NotEmpty(message = "Calendar cbgColor NotEmpty")
    private String cbgColor;

    @NotNull(message = "Calendar cdragBgColor NotNull")
    @NotEmpty(message = "Calendar cdragBgColor NotEmpty")
    private String cdragBgColor;

    @NotNull(message = "Calendar cborderColor NotNull")
    @NotEmpty(message = "Calendar cborderColor NotEmpty")
    private String cborderColor;

}
