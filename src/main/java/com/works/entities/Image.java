package com.works.entities;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@ApiModel(value = "Resim Model",description = "Resim işlemleri(Ekleme,Güncelleme,Silme) için Kullanılır.")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iid", nullable = false)
    private Integer iid;


    private String imagename;
}
