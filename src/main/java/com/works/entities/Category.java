package com.works.entities;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Kategori Model",description = "Yeni Kategori Ekleme için Kullanılır.")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caid", nullable = false)
    private Integer caid;


    @NotNull(message = "Category categoryname NotNull")
    @NotEmpty(message = "Category categoryname NotEmpty")
    private String categoryname;


}
