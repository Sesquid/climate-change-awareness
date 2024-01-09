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
    @Column(name = "id")
    private int id;
    @Column(name = "statename")
    private String stateName;
    @ManyToOne
    @JoinColumn(name = "countryid", foreignKey = @ForeignKey(name = "fk_state_to_country"), referencedColumnName = "id")
    private Country country;
    @Column(name = "countryname")
    private String countryName;
}
