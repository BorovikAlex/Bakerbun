package client.controllers;

import client.entityClass.Order;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class StewardOrderController {
    @FXML
    private Button backButton;

    @FXML
    private TableView<Order> stewardOrderTable;

    @FXML
    private TableColumn<Order, String> idColumn;

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private TableColumn<Order, String> seatsColumn;

    @FXML
    private TableColumn<Order, String> dateColumn;

    @FXML
    private TableColumn<Order, String> timeColumn;

    @FXML
    private TableColumn<Order, String> tableColumn;

    @FXML
    private TableColumn<Order, String> stewardColumn;

    @FXML
    private TableColumn<Order, String> accountColumn;

    @FXML
    private TextField search;

    @FXML
    private Button addStewardB;

    @FXML
    private Button addAccountB;

    @FXML
    private Button orderB;

    @FXML
    private Button tableB;

    @FXML
    void initialize() {
        fillTableView();
        search.textProperty().addListener((observable, oldValue, newValue) ->
                filterList(oldValue, newValue));
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getStewardOrder");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 10);
            Order order = new Order();
            order.setId_order(Integer.valueOf(infoString[0]));
            order.setName(infoString[1]);
            order.setSeats(Integer.valueOf(infoString[2]));
            order.setDate(infoString[3]);
            order.setTime(infoString[4]);
            order.setTable(Integer.valueOf(infoString[5]));
            order.setSteward(infoString[6]);
            order.setAccount(Double.valueOf(infoString[7]));
            orders.add(order);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_order"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("table"));
        stewardColumn.setCellValueFactory(new PropertyValueFactory<>("steward"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
        stewardOrderTable.setItems(orders);
    }

    public void filterList(String oldValue, String newValue) {
        ObservableList<Order> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (Order order : stewardOrderTable.getItems()) {
                String filter = order.getSteward();
                if (filter.toUpperCase().contains(newValue) || filter.toUpperCase().contains(newValue)) {
                    filteredList.add(order);
                }
            }
            stewardOrderTable.setItems(filteredList);
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }

    @FXML
    void addAccount() {
        addAccountB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("setAccount", "");
    }

    @FXML
    void addSteward() {
        addStewardB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("setSteward", "");
    }

    @FXML
    void order() {
        orderB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientsOrder", "");
    }

    @FXML
    void table() {
        tableB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("setTable", "");
    }
}
