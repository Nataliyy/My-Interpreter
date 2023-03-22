package com.example.interpreter.Controllers;

import com.example.interpreter.Application;
import com.example.interpreter.Word.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class ControllerCard {
    public VBox root;

    public void btnHomeСlicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Menu.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());
    }

    public void btnStartClicked(ActionEvent actionEvent) throws IOException { // нажата кнопка начать
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("CardGame.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());

    }

    //далее действия на сцене с самой игрой
}

