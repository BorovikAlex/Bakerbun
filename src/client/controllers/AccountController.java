package client.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.entityClass.Order;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccountController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Order> accountTable;

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private TableColumn<Order, String> tableColumn;

    @FXML
    private TableColumn<Order, String> dateColumn;

    @FXML
    private TableColumn<Order, String> timeColumn;

    @FXML
    private TableColumn<Order, String> stewardColumn;

    @FXML
    private TableColumn<Order, String> accountColumn;

    @FXML
    void initialize() {
        fillTableView();
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getAccount");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 6);
            Order order = new Order();
            order.setName(infoString[0]);
            order.setTable(Integer.valueOf(infoString[1]));
            order.setDate(infoString[2]);
            order.setTime(infoString[3]);
            order.setSteward(infoString[4]);
            order.setAccount(Double.valueOf(infoString[5]));
            orders.add(order);
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("table"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        stewardColumn.setCellValueFactory(new PropertyValueFactory<>("steward"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
        accountTable.setItems(orders);
    }
    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}

