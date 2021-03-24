package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String userName;
    private String password;
    private int phoneNumber;
    private String address;
    private int zipCode;
    private String city;
    //todo make final
    private boolean gdprAccepted;
    //todo make final
    private final String role = "ROLE_USER";
}
