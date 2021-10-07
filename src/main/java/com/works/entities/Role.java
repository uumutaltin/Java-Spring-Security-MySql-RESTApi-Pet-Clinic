package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ApiModel(value = "Kullanıcı Model",description = "Kullanıcı işlemleri(Ekleme,Güncelleme,Silme) için Kullanılır.")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<Users> users;


}
