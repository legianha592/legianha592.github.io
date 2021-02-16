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
public class User {
    @Id @GeneratedValue
    private long id;
    private String user_name;
    private String password;
    private Date created_date;
    private Date modified_date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "wallet")
    private List<Wallet> listWallet = new ArrayList<>();
}
