package com.voting.votingsystem.optimizations;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashTableExample {
    public static void main(String[] args) {


        Hashtable<String, Integer> hashtable = new Hashtable<>();

        //Inserting key-value pairs
        hashtable.put("Alice", 25);
        hashtable.put("Bob", 30);
        hashtable.put("Charlie", 28);

        // Retrieving values
        int aliceAge = hashtable.get("Alice");
        System.out.println("Alice's Age: " + aliceAge);

        hashtable.put("Bob", 31);

        // Removing a key
        hashtable.remove("Charlie");


        boolean isBobPresent = hashtable.containsKey("Bob");


        Enumeration<String> enumeration = hashtable.keys();
        while(enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            System.out.println(
                    key
            );
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (Map.Entry<String, Integer> entry :  hashtable.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }


}
