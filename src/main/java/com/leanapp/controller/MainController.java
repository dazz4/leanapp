package com.leanapp.controller;

import com.leanapp.service.ProfileService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Getter
public class MainController implements Initializable {

    @Autowired
    private ProfileService profileService;

    @FXML
    private Tab weightLogTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (profileService.getAllProfiles().size() == 0) {
            weightLogTab.setDisable(true);
        }
    }
}
