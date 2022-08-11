package com.example.creditapplicationsystem.Repository;

import com.example.creditapplicationsystem.Model.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,String> {

}
