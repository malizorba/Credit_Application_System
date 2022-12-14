package com.example.creditapplicationsystem.Model.Entity;

import com.example.creditapplicationsystem.Model.Enum.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Table(name = "Credit")
public class CreditApplication implements Serializable {

//    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Double CreditAmount;

    @OneToOne(mappedBy = "loanList",cascade = CascadeType.ALL)
//    @JoinColumn(name = "national_identity_number", referencedColumnName = "national_identity_number")
    private Customer customer;



    @Column(name = "applied_date")
    @CreationTimestamp
    private LocalDateTime dateofApply;



    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @Column
    private Integer creditScore;

    @Column
    private Integer creditLimit;



}
