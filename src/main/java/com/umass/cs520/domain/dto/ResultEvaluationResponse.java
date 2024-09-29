package com.umass.cs520.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultEvaluationResponse {
    private boolean success;
    private String message;
}