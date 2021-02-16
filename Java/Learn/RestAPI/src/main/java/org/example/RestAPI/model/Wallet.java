package org.example.RestAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id @GeneratedValue
    private long id;
    private String wallet_name;
    private Date created_date;
    private Date modified_date;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "record")
    private List<Record> listRecord = new ArrayList<>();
}
