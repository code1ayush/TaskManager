package com.Ayush.TaskManager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sentiment {

    String email;
    String sentiment;
    String subject;
}
