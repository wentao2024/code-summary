package com.umass.cs520.domain;

import java.util.Date;
import lombok.Data;

@Data
public class CodeSummarizations {
    private Integer summarizationid;

    private Integer userid;

    private String codetext;

    private String summarization;

    private Date creationdate;
}