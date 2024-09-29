package com.umass.cs520.domain.dto;

import lombok.Data;

@Data
public class CodeSummarizationRequest {
    private String codeInput;
    private int numberOfResults;
}
