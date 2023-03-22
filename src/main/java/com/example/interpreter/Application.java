package com.example.interpreter;

import com.example.interpreter.Word.CardService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class Application extends javafx.application.Application {

    public static CardService cardService;

    private static ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws IOException {

        context = SpringApplication.run(Application.class);
        cardService = context.getBean(CardService.class);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 650);
        stage.setTitle("My Interpreter!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    public static void main(String[] args) {
        launch();
    }
}
