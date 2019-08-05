package com.leanapp.service;

import com.leanapp.domain.Measurements;
import com.leanapp.domain.User;
import com.leanapp.domain.exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTestSuite {

    @Autowired
    private UserService userService;

    @Autowired
    private WeightService weightService;

    @Test
    public void shouldAddToDatabase() throws UserNotFoundException {
        //Given
        Measurements measurements = new Measurements(65L, 20L, 170L, "male");
        User user = new User("Dazz", measurements);
        userService.saveOrUpdate(user);
        //When
        User tempUser = userService.getUser(user.getId())
                .orElseThrow(UserNotFoundException::new);
        //Then
        assertEquals("Dazz", tempUser.getName());
        //Cleanup
        userService.delete(user.getId());
    }

    @Test
    public void shouldUpdate() throws UserNotFoundException {
        //Given
        Measurements measurements = new Measurements(65L, 20L, 170L, "male");
        User user = new User("Dazz", measurements);
        userService.saveOrUpdate(user);

        Measurements updateMeasure = new Measurements(77L, 22L, 180L, "male");
        User updateUser = new User(user.getId(), "Kamil", updateMeasure, new ArrayList<>());
        userService.saveOrUpdate(updateUser);
        //When
        User tempUser = userService.getUser(user.getId())
                .orElseThrow(UserNotFoundException::new);
        //Then
        assertEquals("Kamil", tempUser.getName());
        assertEquals(Long.valueOf(77), tempUser.getMeasurements().getCurrentWeight());
        //Cleanup
        userService.delete(user.getId());
    }
}