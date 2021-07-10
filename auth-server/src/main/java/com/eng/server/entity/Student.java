package com.eng.server.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {

    private Integer sno;
    private String sName;
    private String password;
}
