package com.umass.cs520.domain;

import lombok.Data;

@Data
public class Users {
    private Integer userid;

    private String username;

    private String password;

    private Integer role = 0;
}