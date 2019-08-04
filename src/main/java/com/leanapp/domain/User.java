package com.leanapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Measurements measurements;

    @OneToMany(mappedBy = "user")
    private List<Weight> weights = new ArrayList<>();

    public User(String name, Measurements measurements) {
        this.name = name;
        this.measurements = measurements;
    }
}
