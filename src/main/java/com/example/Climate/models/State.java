package com.example.Climate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "statename")
    private String stateName;
    @ManyToOne
    @JoinColumn(name = "countryname", referencedColumnName = "countryname")
    private Country country;
}
