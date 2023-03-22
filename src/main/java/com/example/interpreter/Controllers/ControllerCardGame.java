package com.example.interpreter.Controllers;

import com.example.interpreter.Application;
import com.example.interpreter.Word.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCardGame implements Initializable {
    public VBox root;
    public TextField CardField;

    private List<Card> cards;
    private int currentCardIndex = 0;

    boolean isEnglishWord = true;

    public void btnTurnOverClicked(ActionEvent actionEvent) {
        Card c = cards.get(this.currentCardIndex);
        if (isEnglishWord) {
            this.CardField.setText(c.getRuWord());
        } else {
            this.CardField.setText(c.getEnWord());
        }
        isEnglishWord = !isEnglishWord;
    }


    public void btnNextClicked(ActionEvent actionEvent) {
        if (this.cards.size() > this.currentCardIndex + 1) {
            this.currentCardIndex++;
            loadCard();
        } else {
            startGame();
        }
    }

    public void btnHomeСlicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Menu.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());
    }

    public void loadCard() {
        isEnglishWord = true;
        Card c = cards.get(this.currentCardIndex);
        Application.cardService.markViewed(c);
        this.CardField.setText(c.getEnWord());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        startGame();

    }

    void startGame(){

        cards = Application.cardService.getCardsToLearn();
        Collections.sort(this.cards, Comparator.comparingInt(c -> c.timesViewed));

        if (this.cards.size() == 0) {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Menu.fxml"));
            SubScene scene = null;
            try {
                scene = new SubScene(fxmlLoader.load(), 500, 650);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.root.getChildren().clear();
            this.root.getChildren().add(scene.getRoot());
            return;
        }

        loadCard();

    }
}
