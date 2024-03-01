package com.voting.votingsystem.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateVotingStatus {
    private String votingStatus;
    private String password;
}
