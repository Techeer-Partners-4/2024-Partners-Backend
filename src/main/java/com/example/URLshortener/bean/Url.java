package com.example.URLshortener.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Url {
    private Integer urlid;
    private String originurl;
    private String hash;
    private Date joindate;
    private boolean active;
}
