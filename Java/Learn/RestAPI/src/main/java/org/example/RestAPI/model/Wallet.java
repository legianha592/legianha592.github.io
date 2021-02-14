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
public class Wallet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String wallet_name;
    private Date created_date;
    private Date modified_date;
}
