package client.controllers;

import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SampleController {

    @FXML
    private Button loginButton;

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
    }

    @FXML
    void loginButt() {
        loginButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("login", "");

    }


}