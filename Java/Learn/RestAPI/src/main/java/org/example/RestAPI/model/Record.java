package org.example.RestAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
