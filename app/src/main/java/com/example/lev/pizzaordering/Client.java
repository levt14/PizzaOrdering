package com.example.lev.pizzaordering;

import java.io.Serializable;

/**
 * Created by LEV on 17/12/2017.
 */
@SuppressWarnings("serial")
public class Client implements Serializable {

    private String name;
    private String phoneNumber;
    private String email;


    public Client() {
        this.name = "unknown";
        this.phoneNumber = "unknown";
        this.email = "unknown";
    }

    public Client(String name, String phone, String email) {
        this.name = name;
        this.phoneNumber = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public void setPhone(String phone) {
        this.phoneNumber = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

