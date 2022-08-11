package com.example.creditapplicationsystem.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Table(name = "customer")

public class Customer implements Serializable {
    @Id
    @Column(name = "national_identity_number",length = 11,updatable = false, nullable = false)
    @NotBlank(message = "national identity number can not be blank")
    @Pattern(regexp = "[1-9][0-9]{10}")
    private String IdentityNationalNumber;

//    @JsonIgnore
//    private Integer creditScore;

    @Column(name = "name")
    private String name;


    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    @Pattern(regexp = "^[0-9]{10}", message = "Phone number needs to be 10 digits and can only contain only numbers.")
    private String phoneNumber;

//    @NotBlank
    @Column(name = "monthly_salary")
    private Double monthlySalary;

    @Email
    private String email;

    @Transient
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CreditApplication> loanList;

    @Transient
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CreditResult> creditResultList;



}
