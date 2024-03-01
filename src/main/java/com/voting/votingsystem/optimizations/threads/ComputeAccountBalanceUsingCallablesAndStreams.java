package com.voting.votingsystem.optimizations.threads;


import com.voting.votingsystem.utils.AnalyticsAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComputeAccountBalanceUsingCallablesAndStreams {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Create a thread pool to process multiple bank accounts . Implementation of the steam API for Declarative way of programming.

        ExecutorService executorService = Executors.newFixedThreadPool(2);


        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount(1000));
        accounts.add(new BankAccount(1500));
        accounts.add(new BankAccount(2000));

        // Create a Callable task for calculating total balance, Submit the task to the executor and get a Future representing the result  and  Perform other tasks while waiting for the result

        Callable<Integer> balanceCalculatorCallable = new BalanceCalculatorCallable(accounts);

        Future<Integer> future = executorService.submit(balanceCalculatorCallable);

        System.out.println("Performing other tasks...");

        try {

            int totalBalance = future.get(); //Gets the result from the Future (blocking until the computation is complete : Throws the following exceptions :
            System.out.println("Total Balance: " + totalBalance);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Re-interrupt the thread to avoid the interruption being lost to be checked by other parts of the code.

        } catch (ExecutionException e) {
            AnalyticsAPI.analyticsLogging(
                   "ExecutionException : programmer error in call() function : Exception occurred during execution: " + e.getMessage()
            );

        } catch (Exception e) {
            AnalyticsAPI.analyticsLogging(
                    "Some Other Exception occurred during execution: " + e.getMessage()
            );

        }
        finally {
            executorService.shutdown();
        }
    }
}

