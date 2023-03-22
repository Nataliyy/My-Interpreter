package com.example.interpreter.Controllers;

import com.example.interpreter.Application;
import com.example.interpreter.Word.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerTable implements Initializable {
    public VBox root;
    public TableColumn TableRuWord;
    public TableColumn TableEnWord;
    public TableView WTable;
    public Button btnDelW;

    public void btnHomeClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Menu.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableRuWord.setCellValueFactory(
                new PropertyValueFactory<>("RuWord"));
        TableEnWord.setCellValueFactory(
                new PropertyValueFactory<>("EnWord"));

        WTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnDelW.setDisable(false);
                lastSelectedCard = (Card) newSelection;
                //WTable.getSelectionModel().clearSelection();
            } else {
                btnDelW.setDisable(true);
            }
        });

        WTable.getItems().addAll(Application.cardService.getCards());
        btnDelW.setDisable(true);
    }

    Card lastSelectedCard = null;

    public void btnDelWClicked(ActionEvent actionEvent) { //нажали кнопку удалить слово
        if (lastSelectedCard != null) {
            Application.cardService.removeCard(lastSelectedCard);
            WTable.getItems().clear();
            WTable.getItems().addAll(Application.cardService.getCards());
        }
    }

    static class Word {
        String EnWord;
        String RuWord;

        Word(String EnWord, String RuWord) {
            this.EnWord = EnWord;
            this.RuWord = RuWord;
        }

        Word(Scanner in) {
            this.EnWord = in.nextLine();
            this.RuWord = in.nextLine();
        }

    }
}

