package org.example.RestAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float amount;
    private String title;
    private String note;
    private Date created_date;
    private Date modified_date;
}
