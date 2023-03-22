package com.example.interpreter.Controllers;

import com.example.interpreter.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.SubScene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {

    public VBox root;
    public LineChart LineChart;

    @FXML
    public void btnTraClicked(ActionEvent actionEvent) throws IOException { //на переводчик
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Translator.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());
    }

    public void btnCarClicked(ActionEvent actionEvent) throws IOException { // кнопка на карточки
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("CardMenu.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());
    }

    public void btnDicClicked(ActionEvent actionEvent) throws IOException { // кнопка на общий словарь
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Table.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());

    }

    public void btnQueClicked(ActionEvent actionEvent) throws IOException { // кнопка что такое кривая забывания
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Inf.que.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());
    }


    public void btnInfClicked(ActionEvent actionEvent) throws IOException { // кнопка промежутки между повторениями
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Time.fxml"));
        SubScene scene = new SubScene(fxmlLoader.load(), 500, 650);
        this.root.getChildren().clear();
        this.root.getChildren().add(scene.getRoot());
    }


    public void Line() {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 120, 50);

        xAxis.setLabel("Временной интервал");
        yAxis.setLabel("Процент сохранения в памяти");

        LineChart linechart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();


        series.getData().add(new XYChart.Data("сразу", 100));
        series.getData().add(new XYChart.Data("20 мин", 58));
        series.getData().add(new XYChart.Data("1 час", 45));
        series.getData().add(new XYChart.Data("1 день", 34));
        series.getData().add(new XYChart.Data("2 дня", 30));
        series.getData().add(new XYChart.Data("6 дней", 27));
        series.getData().add(new XYChart.Data("1 месяц", 20));

        series.setName("% запомненного спустя разные временные интервалы");

        this.LineChart.getData().add(series);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Line();
    }
}