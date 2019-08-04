package com.leanapp.service;

import com.leanapp.domain.User;
import com.leanapp.domain.Weight;
import com.leanapp.domain.exceptions.WeightLogNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeightServiceTest {

    @Autowired
    private WeightService weightService;

    @Autowired
    private UserService userService;

    public User addUser() {
        User user = new User();
        userService.saveOrUpdate(user);
        return user;
    }

    @Test
    public void shouldAddToDatabase() throws WeightLogNotFoundException {
        //Given
        User user = addUser();
        Weight weight = new Weight(55L, LocalDate.now(), user);
        weightService.saveOrUpdate(weight);
        //When
        Weight tempWeight = weightService.getWeight(weight.getId())
                .orElseThrow(WeightLogNotFoundException::new);
        //Then
        assertEquals(Long.valueOf(55), tempWeight.getWeight());
        assertEquals(LocalDate.now(), tempWeight.getDate());
    }
}