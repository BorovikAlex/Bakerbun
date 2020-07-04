package client.controllers;

import client.entityClass.Steward;
import client.entityClass.Users;
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

public class ClientsController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Users> clientsTable;

    @FXML
    private TableColumn<Users, String> nameColumn;

    @FXML
    private TableColumn<Users, String> surnameColumn;

    @FXML
    private TableColumn<Users, String> loginColumn;

    @FXML
    void initialize() {
        fillTableView();
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getUsers");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Users> users = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 4);
            Users user = new Users();
            user.setName(infoString[0]);
            user.setSurname(infoString[1]);
            user.setLogin(infoString[2]);
            users.add(user);
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        clientsTable.setItems(users);
    }
    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}
