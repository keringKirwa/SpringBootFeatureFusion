package com.voting.votingsystem.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voting.votingsystem.Entities.Body;
import com.voting.votingsystem.Entities.Item;
import com.voting.votingsystem.Entities.LoginRequest;
import com.voting.votingsystem.UserDetailsServiceImpl;
import com.voting.votingsystem.config.securityconfig.JWTInterpreter;
import com.voting.votingsystem.utils.AnalyticsAPI;
import com.voting.votingsystem.utils.DateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VotingController {

    private final JWTInterpreter jwtInterpreter;
    private final UserDetailsServiceImpl userDetailsService;


    @PostMapping("/mpesa")
    public String createCallback(@RequestBody String jsonString) {
        DateFormatter dateFormatter = new DateFormatter();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Body paymentDetails = mapper.readValue(jsonString, Body.class);

            int SUCCESS_CODE = 0;
            if (paymentDetails.getStkCallback().getCallback().getCallbackMetadata() != null || paymentDetails.getStkCallback().getCallback().getResultCode() == SUCCESS_CODE) {

                System.out.println("JSON REP for a successful payment ::: " + jsonString);

                ArrayList<Item> paymentDetailsArray = (ArrayList<Item>) paymentDetails.getStkCallback().getCallback().getCallbackMetadata().getItem();


                String receiptNo = paymentDetailsArray.get(1).getValue();
                String unformattedDate = paymentDetailsArray.get(3).getValue();
                String phoneNumber = paymentDetailsArray.get(3).getValue();
                System.out.println(receiptNo);


                String formattedDate = dateFormatter.dateFormatter(unformattedDate);

            } else {
                System.out.println("UNSUCCESSFUL TRANSACTION :: The user cancelled the transaction OR  there is insufficient funds in his or her account !!! ");
                System.out.println("JSON DATA*(UNSUCCESSFUL) :: " + jsonString);
            }

        } catch (Exception ref) {
            AnalyticsAPI.analyticsLogging(ref.getLocalizedMessage());
        }
        return "Success";

    }

    @PostMapping("/auth/register")
    public ResponseEntity<Object> register(@RequestBody LoginRequest loginRequest) {

        /**
         * no auth for the register  page .will just populate the  user  register details to the database.
         */

        try {

            System.out.println("LOGIN PASSWORD :: " + loginRequest.getPassword());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("hello login successful...");

        } catch (BadCredentialsException e) {
            String message = e.getMessage();
            System.out.println(message);
            AnalyticsAPI.analyticsLogging(
                    message
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }
    @GetMapping("/auth/test")
    public String getTest(){
        return "Test Successful ....";
    }
}

