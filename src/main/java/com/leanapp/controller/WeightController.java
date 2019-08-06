package com.leanapp.controller;

import com.leanapp.LeanApp;
import com.leanapp.domain.Weight;
import com.leanapp.domain.exceptions.ProfileNotFoundException;
import com.leanapp.domain.exceptions.WeightLogNotFoundException;
import com.leanapp.service.ProfileService;
import com.leanapp.service.WeightService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
@Getter
public class WeightController implements Initializable {

    private final ProfileService profileService;
    private final WeightService weightService;
    private ObservableList<Weight> obList = FXCollections.observableArrayList();

    @FXML
    private TableView<Weight> weightlog;
    @FXML
    private TableColumn<Weight, SimpleLongProperty> col_id;
    @FXML
    private TableColumn<Weight, SimpleLongProperty> col_weight;
    @FXML
    private TableColumn<Weight, SimpleStringProperty> col_date;
    @FXML
    private TableColumn<Weight, SimpleStringProperty> col_comment;
    @FXML
    private DatePicker weightDate;
    @FXML
    private TextField textFieldWeight;
    @FXML
    private TextField textFieldComment;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    public WeightController(WeightService weightService, LeanApp leanApp, ProfileService profileService) {
        this.weightService = weightService;
        this.profileService = profileService;
    }

    @FXML
    void addWeight(ActionEvent event) throws WeightLogNotFoundException, ProfileNotFoundException {

        Weight weight = new Weight(
                Double.valueOf(textFieldWeight.getText()),
                weightDate.getValue(),
                textFieldComment.getText(),
                profileService.getProfile(1L)
                        .orElseThrow(ProfileNotFoundException::new));

        weightService.saveOrUpdate(weight);

        weightlog.getItems().add(weightService.getWeight(weight.getId())
                .orElseThrow(WeightLogNotFoundException::new));
    }

    @FXML
    public void deleteWeight(ActionEvent actionEvent) {

        Weight selectedItem = weightlog.getSelectionModel().getSelectedItem();

        weightService.delete(selectedItem.getId());
        weightlog.getItems().remove(selectedItem);

    }

    @FXML
    public void updateWeight(ActionEvent actionEvent) {

        Weight selectedItem = weightlog.getSelectionModel().getSelectedItem();

        Weight updateWeight = new Weight(
                selectedItem.getId(),
                Double.valueOf(textFieldWeight.getText()),
                weightDate.getValue(),
                textFieldComment.getText(),
                null);

        int index = weightlog.getSelectionModel().selectedIndexProperty().get();

        weightService.saveOrUpdate(updateWeight);
        obList.set(index, updateWeight);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        obList.addAll(weightService.getAllWeights());

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        weightlog.setItems(obList);

        weightlog.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {

            if (newValue != null) {
                textFieldWeight.setText(observable.getValue().getWeight().toString());
                weightDate.setValue(LocalDate.parse(observable.getValue().getDate().toString()));
                textFieldComment.setText(observable.getValue().getComment());
            }

        }));
    }
}
