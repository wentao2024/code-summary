package com.umass.cs520.domain;

import java.util.Date;
import lombok.Data;

@Data
public class Evaluations {
    private Integer evaluationid;

    private Integer summarizationid;

    private Integer userid;

    private Integer naturalnessscore;

    private Integer usefulnessscore;

    private Integer consistencyscore;

    private String feedback;

    private Date evaluationdate;
}