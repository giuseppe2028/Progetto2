module com.example.progetto2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens com.example.progetto2 to javafx.fxml;
    exports com.example.progetto2;
    opens com.example.progetto2.Autenticazione.Schermate to javafx.fxml;



}