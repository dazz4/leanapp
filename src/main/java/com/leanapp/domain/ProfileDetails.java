package com.leanapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProfileDetails {
    private Double currentWeight;
    private Long age;
    private Double height;
    private String gender;
    private Long bodyFat;
    private Long muscleMass;
    private Long activity;
}
