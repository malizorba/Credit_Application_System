package com.example.creditapplicationsystem.Repository;

import com.example.creditapplicationsystem.Model.Entity.CreditResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditResultRepository extends JpaRepository<CreditResult,Long> {
}
