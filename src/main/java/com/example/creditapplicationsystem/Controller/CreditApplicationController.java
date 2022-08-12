package com.example.creditapplicationsystem.Controller;

import com.example.creditapplicationsystem.Model.Entity.CreditApplication;
import com.example.creditapplicationsystem.Service.impl.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/CreditApplication")
public class CreditApplicationController {
    private final CreditApplicationService creditApplicationService;


    @GetMapping("/all")
    public ResponseEntity getAllACreditApplication() {
        List<CreditApplication> AllApp = creditApplicationService.getAllApply();
        return ResponseEntity.ok(AllApp);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCreditApplicationbyId(@PathVariable("id") Long id) {
        CreditApplication byid = creditApplicationService.getApplyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byid);
    }

//    @PostMapping("/create")
//    public ResponseEntity create(@RequestBody CreditApplication creditApplication) {
//        CreditApplication repsCredit = creditApplicationService.createCreditApplication(creditApplication);
//        if (repsCredit == null) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Application cold not be created");
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(repsCredit);
//
//    }



    @DeleteMapping
    public ResponseEntity deleteCreditApplication (@RequestParam(name = "id")Long id){
        creditApplicationService.deleteCreditApplication(id);
        return ResponseEntity.status(HttpStatus.OK).body("CreditApplication was deleted");
    }
    @GetMapping("/customer/{nationalIdentityNumber}")
    public CreditApplication getCreditApplicationByCustomerId(@PathVariable  String nationalIdentityNumber){
        return creditApplicationService.getCreditApplicationByCustomerId(nationalIdentityNumber);
    }


}
