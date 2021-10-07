package com.works.entities.base;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Embeddable;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseDiary {

    @Id
    private String did;

    private String title;
    private String detail;
    private Date date ;
    private String dtime;

}
