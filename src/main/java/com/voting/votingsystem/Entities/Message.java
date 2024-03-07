package com.voting.votingsystem.Entities;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Message {
    @Id
    private String id;
    private String userId;
    private String stringMessage;


}