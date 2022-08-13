package com.example.creditapplicationsystem.Model.Entity;

import com.example.creditapplicationsystem.Model.Enum.ApprovalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "national_identity_number", referencedColumnName = "national_identity_number")
      @Transient
      private Customer customer;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Credit_Application_Id", referencedColumnName = "id")
    private CreditApplication creditApplication;

}
