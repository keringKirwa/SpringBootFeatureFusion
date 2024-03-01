package com.voting.votingsystem.optimizations.threads;

import java.util.List;
import java.util.concurrent.Callable;

public class BalanceCalculatorCallable implements Callable<Integer> {
    private final List<BankAccount> accounts;

    public BalanceCalculatorCallable(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public Integer call() throws Exception {

        System.out.println("Number of processors : " + Runtime.getRuntime().availableProcessors());

        int totalAccountBalanceComputedInParallel = accounts
                .stream()
                .parallel()
                .mapToInt(BankAccount::getAccountBalance)
                .sum();

        Thread.sleep(2000);
        return totalAccountBalanceComputedInParallel;
    }
}
