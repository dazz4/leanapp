package com.leanapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Weight {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Weight(Double weight, LocalDate date, Profile profile) {
        this.weight = weight;
        this.date = date;
        this.profile = profile;
    }

    public Weight(Double weight, LocalDate date, String comment, Profile profile) {
        this.weight = weight;
        this.date = date;
        this.comment = comment;
        this.profile = profile;
    }

    public Weight(Double weight, LocalDate date, String comment) {
        this.weight = weight;
        this.date = date;
        this.comment = comment;
    }
}
