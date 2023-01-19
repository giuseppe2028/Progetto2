package com.example.GestioneRemoto.FileDiSistema;

import com.example.GestioneRemoto.GestioneImpiegato.Schermate.SchermataVisualizzaImpiegato;
import com.example.GestioneRemoto.GestioneProfilo.Schermate.SchermataVisualizzaProfilo;
import com.example.GestioneRemoto.Start;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;

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
    /*public static void setScene(String fxml, Stage stage){

        Parent root;
try {
    FXMLLoader loader = new FXMLLoader(Start.class.getResource(fxml));
    root = loader.load();
    PreviousSceneController controller = loader.getController();
    Scene scene = new Scene(root);
     stage = (Stage) btn.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    pre
}catch (IOException e){
    e.printStackTrace();
}


    }*/

public static void goBack(){
    scene.getWindow().hide();
}

public static void indietro(String fxml,Stage stage){

    try {
        FXMLLoader loader = new FXMLLoader(Start.class.getResource(fxml));
        stage.setScene(new Scene(loader.load()));
        SchermataVisualizzaProfilo schermataVisualizzaProfilo = loader.getController();
        schermataVisualizzaProfilo.initialize();
        stage.show();
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

}