package com.leanapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Profile {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Embedded
    private ProfileDetails profileDetails;

    @OneToMany(
            targetEntity = Weight.class,
            mappedBy = "profile",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Weight> weights;

    public Profile(String name, ProfileDetails profileDetails) {
        this.name = name;
        this.profileDetails = profileDetails;
    }
}
