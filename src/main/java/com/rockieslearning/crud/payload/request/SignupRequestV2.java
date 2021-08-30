package com.rockieslearning.crud.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Getter
@Setter
public class SignupRequestV2 {
    @NotBlank
    @Size(min = 10, max = 11)
    private String phone;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


    @NotBlank
    @Size(min = 1, max = 40)
    private String firstName;


    @NotBlank
    @Size(min = 1, max = 40)
    private String lastName;

    @NotBlank
    @Size(min = 6, max = 40)
    private String address;



}