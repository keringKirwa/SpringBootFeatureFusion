package com.voting.votingsystem.Services;

import com.voting.votingsystem.Entities.EAddress;
import com.voting.votingsystem.Entities.EBankAccount;
import com.voting.votingsystem.repositories.IAddress;
import com.voting.votingsystem.repositories.IBankAccountRepository;
import com.voting.votingsystem.utils.AnalyticsAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class BankAccountService {
    private static final Random random = new Random();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BANK_ID_LENGTH = 8;
    private final IBankAccountRepository iBankAccountRepository;
    private final IAddress iAddressRepository;
    @Autowired
    public BankAccountService(IBankAccountRepository iBankAccountRepository, IAddress iAddress) {
        this.iBankAccountRepository = iBankAccountRepository;
        this.iAddressRepository = iAddress;
    }

    public static String generateBankId() {
        StringBuilder bankId = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < BANK_ID_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            bankId.append(randomChar);
        }

        return bankId.toString();
    }

    public Object getAllBankAccounts() {
        return iBankAccountRepository.findAll();
    }

    public EBankAccount createAndSave_One_BankAccount() {
        EBankAccount bankAccount = new EBankAccount(
                null,
                "John",
                "peterjohnson@outlook.com",
                30,
                "North",
                2024, // Replace with actual date logic
                "12345",
                1000
        );

        return iBankAccountRepository.save(bankAccount);
    }


    public  void createAndSave_K_Bank_Accounts(int numberOfAccounts) {
        String[] regions = {"Kiambu", "Embu", "Nairobi"};
        int[] expiryYears = {2023, 2024,2025,2026};

        Random random = new Random();

        for (int i = 0; i < numberOfAccounts; i++) {

            EBankAccount bankAccount = new EBankAccount(
                    null,
                    "User" + random.nextInt(1000),
                    "user" + random.nextInt(1000) + "@gmail.com",
                    random.nextInt(50) + 20,
                    regions[random.nextInt(regions.length)],
                    expiryYears[random.nextInt(regions.length)],
                    generateBankId(),
                    random.nextInt(100000)
            );
            try {
                createAndSave_User_Address(iBankAccountRepository.save(bankAccount));

            }catch (Exception e){
                AnalyticsAPI.analyticsLogging(e.getLocalizedMessage());

            }
        }
    }

    public void createAndSave_User_Address(EBankAccount eBankAccount){

        EAddress address = new EAddress(
                null,
                "Street" + random.nextInt(1000),
                "City" + random.nextInt(1000),
                "State" + random.nextInt(1000),
                "ZIP" + random.nextInt(1000),
                "Country" + random.nextInt(1000),
                eBankAccount.getEmailAddress()
        );
        try {

            EAddress savedAddress = iAddressRepository.save(address);

        }catch (Exception e){
            AnalyticsAPI.analyticsLogging(e.getLocalizedMessage());

        }
    }


}
