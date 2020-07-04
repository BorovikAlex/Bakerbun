package client.controllers;

import client.entityClass.Steward;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class StewardController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Steward> managerTable;

    @FXML
    private TableColumn<Steward, String> nameColumn;

    @FXML
    private TableColumn<Steward, String> surnameColumn;

    @FXML
    private TableColumn<Steward, String> loginColumn;

    @FXML
    private Button addB;

    @FXML
    private Button delB;

    @FXML
    void initialize() {
        fillTableView();
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getSteward");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Steward> stewards = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 4);
            Steward steward = new Steward();
            steward.setName(infoString[0]);
            steward.setSurname(infoString[1]);
            steward.setLogin(infoString[2]);
            stewards.add(steward);
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        managerTable.setItems(stewards);
    }

    @FXML
    void add() {
        addB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addSteward", "");
    }

    @FXML
    void del() {
        delB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("delSteward", "");
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}
