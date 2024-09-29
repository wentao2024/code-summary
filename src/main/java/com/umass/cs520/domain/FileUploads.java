package com.umass.cs520.domain;

import java.util.Date;
import lombok.Data;

@Data
public class FileUploads {
    private Integer fileid;

    private Integer userid;

    private String filepath;

    private Date uploaddate;
}