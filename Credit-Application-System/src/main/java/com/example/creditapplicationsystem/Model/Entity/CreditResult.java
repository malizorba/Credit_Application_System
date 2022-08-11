package com.example.creditapplicationsystem.Model.Entity;

import com.example.creditapplicationsystem.Model.Enum.ApprovalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Table(name = "CreditResult")
public class CreditResult {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private Integer creditScore;

    @Column(name = "Credit_Limit")
    private Integer creditLimit;

    @Enumerated
    private ApprovalStatus approvalStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "national_identity_number", referencedColumnName = "national_identity_number")
    private Customer customer;

}
