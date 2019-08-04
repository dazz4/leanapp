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
    private Long weight;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Weight(Long weight, LocalDate date, User user) {
        this.weight = weight;
        this.date = date;
        this.user = user;
    }
}
