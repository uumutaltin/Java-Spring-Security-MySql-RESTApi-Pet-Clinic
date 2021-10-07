package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Hayvan Irk Model",description = "Yeni Hayvan Irkı Ekleme için Kullanılır.")
public class ScheduleCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @NotNull(message = "ScheduleCalandar id NotNull")
    @NotEmpty(message = "ScheduleCalandar id NotEmpty")
    private String id;

    @NotNull(message = "ScheduleCalandar title NotNull")
    @NotEmpty(message = "ScheduleCalandar title NotEmpty")
    private String title;

    @NotNull(message = "ScheduleCalandar isAllDay NotNull")
    private Boolean isAllDay;

    @NotNull(message = "ScheduleCalandar start NotNull")
    @NotEmpty(message = "ScheduleCalandar start NotEmpty")
    private String start;

    @NotNull(message = "ScheduleCalandar end NotNull")
    @NotEmpty(message = "ScheduleCalandar end NotEmpty")
    private String end;

    @NotNull(message = "ScheduleCalandar category NotNull")
    @NotEmpty(message = "ScheduleCalandar category NotEmpty")
    private String category;

    @NotNull(message = "ScheduleCalandar dueDateClass NotNull")
    private String dueDateClass;

    @NotNull(message = "ScheduleCalandar color NotNull")
    @NotEmpty(message = "ScheduleCalandar color NotEmpty")
    private String color;

    @NotNull(message = "ScheduleCalandar bgColor NotNull")
    @NotEmpty(message = "ScheduleCalandar bgColor NotEmpty")
    private String bgColor;

    @NotNull(message = "ScheduleCalandar dragBgColor NotNull")
    @NotEmpty(message = "ScheduleCalandar dragBgColor NotEmpty")
    private String dragBgColor;

    @NotNull(message = "ScheduleCalandar borderColor NotNull")
    @NotEmpty(message = "ScheduleCalandar borderColor NotEmpty")
    private String borderColor;

    @NotNull(message = "ScheduleCalandar location NotNull")
    @NotEmpty(message = "ScheduleCalandar location NotEmpty")
    private String location;

    @NotNull(message = "ScheduleCalandar raw NotNull")
    @NotEmpty(message = "ScheduleCalandar raw NotEmpty")
    private String raw;

    @NotNull(message = "ScheduleCalandar state NotNull")
    @NotEmpty(message = "ScheduleCalandar state NotEmpty")
    private String state;

    @NotNull(message = "ScheduleCalandar calendarId NotNull")
    @NotEmpty(message = "ScheduleCalandar calendarId NotEmpty")
    private String calendarId;

}

