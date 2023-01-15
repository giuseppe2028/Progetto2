package com.example.progetto2.FileDiSistema;

import com.example.progetto2.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class Util {
    public static Scene scene;
    public static void setScene(String fxml, Stage stage, Callback controller){

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(Start.class.getResource(fxml));
            loader.setControllerFactory(controller);
            root = loader.load();
            scene = new Scene(root);
            //lo stage che ho passato lo trasformo come main stage, ovvero lo inizializzo al mainstage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //Lo uso quando ho bisogno di utilizzare una scene nel codice
    public static <T> T setSpecificScene(String fxml, Stage stage, Callback controller){

        Parent root;


        try {
            FXMLLoader loader = new FXMLLoader(Start.class.getResource(fxml));
            loader.setControllerFactory(controller);
            root = loader.load();
            scene = new Scene(root);

            //lo stage che ho passato lo trasformo come main stage, ovvero lo inizializzo al mainstage
            stage.setScene(scene);
            stage.show();
            return loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}