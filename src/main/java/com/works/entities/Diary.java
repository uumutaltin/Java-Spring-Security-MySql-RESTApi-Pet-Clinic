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
@ApiModel(value = "Ajanda Model",description = "Ajanda (Ekleme,Güncelleme,Silme) için Kullanılır.\nRedis (Cache) kullanılıyor.")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "did", nullable = false)
    private Integer did;

    @NotNull(message = "Diary title NotNull")
    @NotEmpty(message = "Diary title NotEmpty")
    private String title;

    @NotNull(message = "Diary detail NotNull")
    @NotEmpty(message = "Diary detail NotEmpty")
    private String detail;

    @NotNull(message = "Diary date NotNull")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date ;

    @NotNull(message = "Diary dtime NotNull")
    @NotEmpty(message = "Diary dtime NotEmpty")
    private String dtime;




}