package com.umass.cs520.domain;

import lombok.Data;

@Data
public class CodeSummarization {
    private int summarizationID;
    private String codeText;
    private String summarization;
    private String creationDate;
    private Integer userId;
    private Evaluations evaluations; // Nested evaluations
}
