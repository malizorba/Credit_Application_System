package com.example.creditapplicationsystem.Model.DTO;

import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Validated
public class CustomerDTO {

    @Pattern(regexp = "[1-9][0-9]{10}")
    private String nationalIdentityNumber;

    @NotBlank(message = "name can not be null")
    private String name;

    @NotBlank(message = "lastname can not be null")
    private String lastName;

    @NotBlank(message = "phone number can not be null")
    private String phoneNumber;

    @NotBlank(message = "Monthly income can not be null")
    private Double monthlySalary;

    @Email
    private String email;

    private CreditApplication loanList;



}
