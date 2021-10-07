package com.works.documents;

import com.works.entities.Pet;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import java.util.List;
import java.util.UUID;


@Document(indexName = "customer")
@Data
public class ElasticCustomer {
    @Id
    private String ceid = UUID.randomUUID().toString();

    @Field(type = FieldType.Integer)
    private Integer cid;

    @Field(type = FieldType.Text)
    private String cname;

    @Field(type = FieldType.Text)
    private String csurname;

    @Field(type = FieldType.Text)
    private String mobile_phone;

    @Field(type = FieldType.Text)
    @Column(unique = true)
    private String  email;

    @Field(type = FieldType.Integer)
    private int tax;

    @Field(type = FieldType.Text)
    private String  tax_administration;

    @Field(type = FieldType.Integer)
    private int ctype;

    @Field(type = FieldType.Text)
    private String cnote;

    @Field(type = FieldType.Text)
    private String cprovince;

    @Field(type = FieldType.Text)
    private String cdistrict;

    @Field(type = FieldType.Text)
    private String caddress;

    @Field(type = FieldType.Integer)
    private int cdiscount;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Pet> pets;
}
