package com.justin;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        CounterService counterServc = new CounterService();
        Counter oc = new Counter();
        counterServc.setObject(oc);
        counterServc.setPeriod(Duration.seconds(1));
        counterServc.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("Getting called : " + event.getSource().getValue() + " times");
                oc.setCount((int) event.getSource().getValue());
            }
        });
        counterServc.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
