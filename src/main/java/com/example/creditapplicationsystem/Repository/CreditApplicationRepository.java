package com.example.creditapplicationsystem.Repository;

import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication,Long> {




}
