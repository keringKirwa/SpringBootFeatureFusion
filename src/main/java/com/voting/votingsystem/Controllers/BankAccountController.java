package com.voting.votingsystem.Controllers;

import com.voting.votingsystem.Entities.LoginRequest;
import com.voting.votingsystem.LoanApplicationThreadLauncher;
import com.voting.votingsystem.Services.BankAccountService;
import com.voting.votingsystem.database_relationships.many_to_one.Course;
import com.voting.votingsystem.database_relationships.many_to_one.Student;
import com.voting.votingsystem.utils.AnalyticsAPI;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    /*Now in spring boot : recommended: Constructor Injection*/

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {

        /*Todo Launch one thread to make a api request to a server for Machine learning and another to request the loan limit */

        LoanApplicationThreadLauncher loanApplicationThreadLauncher = new LoanApplicationThreadLauncher(
                "kkirwa@gmail.com", 100
        );
        LoanApplicationThreadLauncher loanApplicationThreadLauncher1 = new LoanApplicationThreadLauncher(
                "sammy@gmail.com", 300
        );


        loanApplicationThreadLauncher.start();
        loanApplicationThreadLauncher1.start();



        this.bankAccountService = bankAccountService;
    }

    private void addCourse(){

        List<Student> initialStudentList = new ArrayList<>();
        Course course = new Course(null, "kelvin", initialStudentList);
    }

    @PostMapping("/createAndSaveBankAccount")
    @NotNull
    public ResponseEntity<Object> createAndSaveBankAccount(@NotNull @RequestBody LoginRequest loginRequest) {
        try {
            return new ResponseEntity<>(bankAccountService.createAndSave_One_BankAccount(), HttpStatus.OK);


        } catch (BadCredentialsException e) {

            AnalyticsAPI.analyticsLogging(e.getLocalizedMessage());
            return new ResponseEntity<>("Error creating a Bank Account", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/getAllBankAccounts")
    @NotNull
    public ResponseEntity<Object> getAllBankAccounts() {
        try {
            return new ResponseEntity<>(bankAccountService.getAllBankAccounts(), HttpStatus.OK);

        } catch (BadCredentialsException e) {

            AnalyticsAPI.analyticsLogging(e.getLocalizedMessage());
            return new ResponseEntity<>("Error creating a Bank Account", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/getRegionBankAccounts")
    @NotNull
    public ResponseEntity<Object> getRegionBankAccounts() {
        try {
            return new ResponseEntity<>(bankAccountService.getAllBankAccounts(), HttpStatus.OK);


        } catch (BadCredentialsException e) {

            AnalyticsAPI.analyticsLogging(e.getLocalizedMessage());
            return new ResponseEntity<>("Error creating a Bank Account", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/triggerFakeBankAccountGenerator")
    @NotNull
    public ResponseEntity<Object> triggerFakeBankAccountGenerator() {
        try {
            int k = 100;
            bankAccountService.createAndSave_K_Bank_Accounts(k);
            return new ResponseEntity<>("Successfully Created"+ k +"Bank accounts", HttpStatus.OK);


        } catch (Exception e) {

            AnalyticsAPI.analyticsLogging(e.getLocalizedMessage());
            return new ResponseEntity<>("Error creating a Bank Account", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}


