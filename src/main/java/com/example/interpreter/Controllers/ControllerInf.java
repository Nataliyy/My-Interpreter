package com.example.interpreter.Controllers;

import com.example.interpreter.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ControllerInf {

    public VBox root;

    public void btnHomeQueClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Menu.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());
    }
}
