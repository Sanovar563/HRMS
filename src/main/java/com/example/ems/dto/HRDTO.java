package com.example.ems.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;



@Data
public class HRDTO {
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}