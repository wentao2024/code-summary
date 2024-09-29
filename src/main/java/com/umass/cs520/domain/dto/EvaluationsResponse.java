package com.umass.cs520.domain.dto;

import com.umass.cs520.domain.CodeSummarization;
import java.util.List;
import lombok.Data;

@Data
public class EvaluationsResponse {
    private boolean success = true;
    private List<CodeSummarization> evaluations;
}

