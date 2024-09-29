package com.umass.cs520.domain.dto;

import lombok.Data;

@Data
public class Evaluation {
    private String codeSnippet;
    private String summarization;
    private int naturalness;
    private int usefulness;
    private int consistency;
}
