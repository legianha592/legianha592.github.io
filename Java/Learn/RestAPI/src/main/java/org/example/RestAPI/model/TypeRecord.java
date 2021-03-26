package org.example.RestAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "typerecord")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String typerecord_name;
    private String image_url;
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

    @ManyToMany(mappedBy = "setTyperecord")
    private List<Record> listRecord = new ArrayList<>();
}