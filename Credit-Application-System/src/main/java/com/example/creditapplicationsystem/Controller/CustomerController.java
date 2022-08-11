package com.example.creditapplicationsystem.Controller;

import com.example.creditapplicationsystem.Model.DTO.CustomerDTO;
import com.example.creditapplicationsystem.Model.Entity.Customer;
import com.example.creditapplicationsystem.Service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/Customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping({"{nationalIdentityNumber}"})
    public ResponseEntity getCustomer(@PathVariable @Size @Pattern(regexp = "[1-9][0-9]{10}") String nationalIdentityNumber) {
        Customer getCustomer = customerService.getCustomer(nationalIdentityNumber);
        return ResponseEntity.status(HttpStatus.OK).body(getCustomer);
    }
    @GetMapping("/all")
    public ResponseEntity getAllCustomer (){
        List<Customer> AllCustomer=customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(AllCustomer);
    }

    @PostMapping("/create")
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer=customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @PutMapping("/{nationalIdentityNumber}")
    public ResponseEntity updateBySalary(
            @PathVariable String nationalIdentityNumber,
            @RequestBody CustomerDTO customerDTO){
        Customer update=customerService.updateCustomerBySalary(nationalIdentityNumber, customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(update);

    }
    @DeleteMapping
    public ResponseEntity deleteCustomer (@RequestParam(name = "nationalIdentityNumber")String nationalIdentityNumber){
        customerService.deleteCustomer(nationalIdentityNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Customer was deleted");
    }




}
