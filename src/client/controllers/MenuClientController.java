package client.controllers;

import client.entityClass.Menu;
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

public class MenuClientController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Menu> accountTable;

    @FXML
    private TableColumn<Menu, String> typeColumn;

    @FXML
    private TableColumn<Menu, String> dishColumn;

    @FXML
    private TableColumn<Menu, String> weightColumn;

    @FXML
    private TableColumn<Menu, String> costColumn;

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
        ClientInstance.INSTANCE.getInstance().send("getMenuClient");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Menu> menus = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 7);
            Menu menu = new Menu();
            menu.setType(infoString[0]);
            menu.setDish(infoString[1]);
            menu.setCost(Double.valueOf(infoString[2]));
            menu.setWeight(Integer.valueOf(infoString[3]));

            menus.add(menu);
        }
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dishColumn.setCellValueFactory(new PropertyValueFactory<>("dish"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        accountTable.setItems(menus);
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientMenu", "");
    }
}
