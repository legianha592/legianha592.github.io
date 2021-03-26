package org.example.RestAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount;
    private String title;
    private String note;
    private LocalDateTime created_date;
    @PrePersist
    public void prePersist(){
        created_date = LocalDateTime.now();
        modified_date = LocalDateTime.now();
    }
    private LocalDateTime modified_date;
    @PreUpdate
    public void preUpdate(){
        modified_date = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Wallet wallet;
    public void setWallet(Wallet wallet){
        this.wallet = wallet;
    }

    @ManyToMany
    @JoinTable(name = "record_typerecord",
                joinColumns = @JoinColumn(name = "record_id"),
                inverseJoinColumns = @JoinColumn(name = "typerecord_id"))
    private Set<TypeRecord> setTyperecord = new HashSet<>();
    public void addTyperecord(TypeRecord typerecord){
        setTyperecord.add(typerecord);
    }
}
