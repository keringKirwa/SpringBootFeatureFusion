package com.voting.votingsystem.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data

public class Progress {
    @Id
    private String id;
    private String projectId;
    private int amountSpent;
    private String activityName;
    private String createdOn ;
    private String progressImage;
    private String description;

}