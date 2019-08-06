package com.leanapp.controller;

import com.leanapp.domain.Profile;
import com.leanapp.domain.ProfileDetails;
import com.leanapp.domain.exceptions.ProfileNotFoundException;
import com.leanapp.service.ProfileService;
import com.leanapp.service.WeightService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Getter
public class ProfileController implements Initializable {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private WeightService weightService;

    @Autowired
    protected MainController mainController;

    private Profile profile;

    @FXML
    private TextField textFieldProfile;

    @FXML
    private TextField textFieldWeight;

    @FXML
    private TextField textFieldHeight;

    @FXML
    private TextField textFieldGender;

    @FXML
    private TextField textFieldAge;

    @FXML
    private TextField textFieldMuscleMass;

    @FXML
    private TextField textFieldBodyFat;

    @FXML
    private TextField textFieldActivity;

    @FXML
    private Text textMaintenance;

    @FXML
    private Text textDeficit;

    @FXML
    private Text textSurplus;

    @FXML
    private Text textAverage;

    @FXML
    private Text textTotal;

    @FXML
    void saveProfile(ActionEvent event) {
        String profileName = textFieldProfile.getText();
        Double currentWeight = Double.valueOf(textFieldWeight.getText());
        Long age = Long.valueOf(textFieldAge.getText());
        Double height = Double.valueOf(textFieldHeight.getText());
        String gender = textFieldGender.getText();
        Long bodyFat = Long.valueOf(textFieldBodyFat.getText());
        Long muscleMass = Long.valueOf(textFieldMuscleMass.getText());
        Long activity = Long.valueOf(textFieldActivity.getText());

        ProfileDetails details = new ProfileDetails(currentWeight, age, height, gender, bodyFat, muscleMass, activity);

        if (profileService.getAllProfiles().size() == 0) {
            profile = new Profile(profileName, details);
            profileService.saveOrUpdate(profile);
        } else {
            profile = profileService.getAllProfiles().get(0);
            profile.setName(profileName);
            profile.setProfileDetails(details);
            profileService.saveOrUpdate(profile);
        }

        if(mainController.getWeightLogTab().isDisable()){
            mainController.getWeightLogTab().setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (profileService.getAllProfiles().size() != 0) {
            try {
                profile = profileService.getProfile(1L)
                        .orElseThrow(ProfileNotFoundException::new);

                textFieldProfile.setText(profile.getName());
                textFieldWeight.setText(profile.getProfileDetails().getCurrentWeight().toString());
                textFieldHeight.setText(profile.getProfileDetails().getHeight().toString());
                textFieldGender.setText(profile.getProfileDetails().getGender());
                textFieldAge.setText(profile.getProfileDetails().getAge().toString());
                textFieldMuscleMass.setText(profile.getProfileDetails().getMuscleMass().toString());
                textFieldBodyFat.setText(profile.getProfileDetails().getBodyFat().toString());
                textFieldActivity.setText(profile.getProfileDetails().getActivity().toString());

            } catch (ProfileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
