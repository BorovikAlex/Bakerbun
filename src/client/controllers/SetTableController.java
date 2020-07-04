package client.controllers;

import client.entityClass.Table;
import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class SetTableController {

    @FXML
    private ChoiceBox<String> tableBox;

    @FXML
    private ChoiceBox<String> statusBox;

    @FXML
    private Button backButton;

    @FXML
    private Button addB;

    @FXML
    private TableView<Table> accountTable;

    @FXML
    private TableColumn<Table, String> tableColumn;

    @FXML
    private TableColumn<Table, String> statusColumn;

    @FXML
    void initialize() {
        fillTableView();
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getTable");
        ArrayList<String> tableList = client.receiveResultList();
        client.send("getStatus");
        ArrayList<String> statusList = client.receiveResultList();
        ObservableList<String> table = FXCollections.observableArrayList(tableList);
        ObservableList<String> status = FXCollections.observableArrayList(statusList);
        tableBox.setItems(table);
        statusBox.setItems(status);

    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getTables");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Table> tables = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 3);
            Table table = new Table();
            table.setTable(Integer.valueOf(infoString[0]));
            table.setStatus(infoString[1]);
            tables.add(table);
        }
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("table"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        accountTable.setItems(tables);
    }

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (tableBox.getValue() == null || statusBox.getValue() == null) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("setTable " + tableBox.getValue() + " " + statusBox.getValue());
        AlertWindow.display("Данные обновлены успешно!");
        ClientInstance.INSTANCE.getInstance().send(info.toString());

        if (!ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Ошибка обновления");
        }
        else {
            tableBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("setTable", "");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("stewardOrder", "");
    }
}
