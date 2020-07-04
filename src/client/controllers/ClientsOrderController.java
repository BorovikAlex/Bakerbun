package client.controllers;

import client.entityClass.Menu;
import client.entityClass.Order;
import client.sample.AlertWindow;
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

public class ClientsOrderController {

    @FXML
    private TableView<Order> stewardOrderTable;

    @FXML
    private TableColumn<Order, String> idColumn;

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private TableColumn<Order, String> tableColumn;

    @FXML
    private TableColumn<Order, String> dishColumn;

    @FXML
    private TableColumn<Order, String> amountColumn;

    @FXML
    private TableColumn<Order, String> statusColumn;

    @FXML
    private Button doneB;

    @FXML
    private TextField status;

    @FXML
    private TextField dish;

    @FXML
    private TextField search;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Menu> accountTable;

    @FXML
    private TableColumn<Menu, String> dishcostColumn;

    @FXML
    private TableColumn<Menu, String> costColumn;

    @FXML
    void initialize() {
        fillTableView();
        fillTableViewCosts();
        search.textProperty().addListener((observable, oldValue, newValue) ->
                filterList(oldValue, newValue));
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getClientsOrder");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 10);
            Order order = new Order();
            order.setId_order(Integer.valueOf(infoString[0]));
            order.setName(infoString[1]);
            order.setTable(Integer.valueOf(infoString[2]));
            order.setDish(infoString[3]);
            order.setAmount(Integer.valueOf(infoString[4]));
            order.setStatus(infoString[5]);
            orders.add(order);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_order"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("table"));
        dishColumn.setCellValueFactory(new PropertyValueFactory<>("dish"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        stewardOrderTable.setItems(orders);
    }

    public void fillTableViewCosts() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getCosts");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Menu> menus = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 3);
            Menu menu = new Menu();
            menu.setDish(infoString[0]);
            menu.setCost(Double.valueOf(infoString[1]));
            menus.add(menu);
        }
        dishcostColumn.setCellValueFactory(new PropertyValueFactory<>("dish"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        accountTable.setItems(menus);
    }

    public void filterList(String oldValue, String newValue) {
        ObservableList<Order> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (Order order : stewardOrderTable.getItems()) {
                String filter = order.getName();
                if (filter.toUpperCase().contains(newValue) || filter.toUpperCase().contains(newValue)) {
                    filteredList.add(order);
                }
            }
            stewardOrderTable.setItems(filteredList);
        }
    }

    public void done() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (dish.getText().equals("")) {
            AlertWindow.display("Вы не заполнили поле!");
            return;
        }
        StringBuilder info = new StringBuilder();
        info.append("setDish " + dish.getText() +
                " " + status.getText());
        AlertWindow.display("Данные изменены успешно!");
        ClientInstance.INSTANCE.getInstance().send(info.toString());
        if (!ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Ошибка изменения");
        }
        else {
            dish.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientsOrder", "");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("stewardOrder", "");
    }
}
