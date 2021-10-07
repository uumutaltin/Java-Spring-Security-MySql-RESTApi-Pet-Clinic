package com.works.property;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@ApiModel(value = "Sale Model",description = "")
public interface SaleProperties {


     String getbill_id();


     Integer getcus_id();


     Integer getamount();


     Boolean getopestatus();


     Integer getpaymenttype();


     Date getdate();


    String getnote() ;


    String getcname();


}
