package com.umass.cs520.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeSummarizationResponse {
    private boolean success;
    private String[] summarizations;
}
