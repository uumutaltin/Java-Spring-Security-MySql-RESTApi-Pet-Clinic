package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@ApiModel(value = "Kullanıcı Model",description = "Kullanıcı işlemleri(Ekleme,Güncelleme,Silme) için Kullanılır.")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Integer uid;

    @NotNull(message = "User username NotNull")
    @NotEmpty(message = "User username NotEmpty")
    private String username;

    @NotNull(message = "User usersurname NotNull")
    @NotEmpty(message = "User usersurname NotEmpty")
    private String usersurname;

    @NotNull(message = "User useremail NotNull")
    @NotEmpty(message = "User useremail NotEmpty")
    private String useremail;

    @NotNull(message = "User password NotNull")
    @NotEmpty(message = "User password NotEmpty")
    private String password;

    private boolean enabled;
    private boolean tokenExpired;

    @NotNull(message = "User userphone NotNull")
    @NotEmpty(message = "User userphone NotEmpty")
    private String userphone;
    private String userstatus;

    @NotNull(message = "User userimage NotNull")
    @OneToOne(cascade = CascadeType.REMOVE)
    private Image userimage;

    @NotNull(message = "User roles NotNull")
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "uid"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "rid"))
    private List<Role> roles;
}
