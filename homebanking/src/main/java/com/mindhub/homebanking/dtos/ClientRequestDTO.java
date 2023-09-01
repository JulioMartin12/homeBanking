package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.RoleType;

import java.util.Set;

public class ClientRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
