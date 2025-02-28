package com.example.studentsoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    static OverviewController overviewController;
    static Stage primaryStage;

    /** Startet stage/Program */
    @Override
    public void start(Stage stage){
        primaryStage = stage;
        openLoginWindow();
    }
    /** Methode zum Öffnen des Login Fensters dort kann man sich mit passendem Username und Password anmelden*/
    public static void openLoginWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            primaryStage.setResizable(false);
            primaryStage.setWidth(400);
            primaryStage.setHeight(300);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Methode zum Öffnen des Dashboards, dort wird eine Liste mit allen Studenten eines Jahrgangs angezeigt, ein Button
     * der einem zum add Student Fenster weiterleitet und ein Logout Button der einen auf das Loginfenster zurückführt */
    public static void openOverviewWindow(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("overview.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
//---------------------------------------Controller zuweisung-----------------------------------------------------------
            overviewController = fxmlLoader.getController();
//----------------------------------------------------------------------------------------------------------------------
            // Fenstergröße & Positionierung
            primaryStage.setResizable(false);
            primaryStage.setWidth(800);
            primaryStage.setHeight(800);
            primaryStage.centerOnScreen();

            primaryStage.setTitle("SAS - Student Administration Software");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(); // Startet Program

    }
}