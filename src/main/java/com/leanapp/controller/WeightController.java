package com.leanapp.controller;

import com.leanapp.domain.Weight;
import com.leanapp.service.WeightService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WeightController implements Initializable {

    @FXML
    private TableView<Weight> weightlog;

    @FXML
    private TableColumn<Weight, String> col_id;

    @FXML
    private TableColumn<Weight, String> col_weight;

    @FXML
    private TableColumn<Weight, String> col_date;
    private final WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }

    private ObservableList<Weight> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        obList.addAll(weightService.getAllWeights());

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        weightlog.setItems(obList);
    }
}
