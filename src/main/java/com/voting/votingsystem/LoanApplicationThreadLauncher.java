package com.voting.votingsystem;


import com.voting.votingsystem.utils.AnalyticsAPI;

public class LoanApplicationThreadLauncher extends Thread {
    private final String emailAddress;
    private final double amount;

    public LoanApplicationThreadLauncher(String emailAddress, double amount) {
        this.emailAddress = emailAddress;
        this.amount = amount;
    }

    public static void main(String[] args) {

        String userEmail = "user@example.com";
        double loanAmount = 5000.0;

        LoanApplicationThreadLauncher threadLauncher = new LoanApplicationThreadLauncher(userEmail, loanAmount);
        threadLauncher.start();
    }

    @Override
    public void run() {
        System.out.println("Processing loan application for email: " + emailAddress + " and amount: " + amount);


        for (int i = 0; i <= 5; i++) {
            System.out.println("Step " + i + " of processing...");

        }
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {

            AnalyticsAPI.analyticsLogging(e.getMessage());
        }

        System.out.println("Loan application processing completed for email: " + emailAddress);
    }
}
