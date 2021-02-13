package org.example.RestAPI.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
public class User {
    private long id;
    private String user_name;
    private String password;
    private Date created_date;
    private Date modified_date;
}
