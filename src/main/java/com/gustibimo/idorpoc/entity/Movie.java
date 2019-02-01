package com.gustibimo.idorpoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Movie {

    /**
     * Indicate this field must never serialized
     */
    @JsonIgnore
    private String backendId;

    private String name;
    private int year;
    private String producer;

}
