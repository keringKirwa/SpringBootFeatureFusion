package com.voting.votingsystem.kafka;


public enum KafkaConsumerGroup {
    GROUP_1("group-1"),
    GROUP_2("group-2"),
    GROUP_3("group-3");

    public static final String GROUP_1_VALUE = "group-1";
    public static final String GROUP_2_VALUE = "group-2";
    public static final String GROUP_3_VALUE = "group-3";

    public final String label;
    private KafkaConsumerGroup(String label) {
        this.label = label;
    }

}

