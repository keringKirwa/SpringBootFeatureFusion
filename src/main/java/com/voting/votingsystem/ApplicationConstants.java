package com.voting.votingsystem;


import org.springframework.stereotype.Service;

@Service
public class ApplicationConstants {

    public static final int PARTITION_COUNT = 3;
    public static final int REPLICATION_FACTOR = 1;


    public static final String BOOTSTRAP_SERVER = "kafka:29092";
}
