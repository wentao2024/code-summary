package com.umass.cs520.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonFormat(with = {JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES})
public class ResultEvaluationRequest {
    private String codeText;
    private String summarization;
    private String feedback;
    private int naturalness;
    private int usefulness;
    private int consistency;
}


