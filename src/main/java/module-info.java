module com.example.GestioneRemoto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens com.example.GestioneRemoto to javafx.fxml;
    exports com.example.GestioneRemoto;
    opens com.example.GestioneRemoto.GestioneAutenticazione.Schermate to javafx.fxml;
    exports com.example.GestioneRemoto.GestioneProfilo.Control;
    opens com.example.GestioneRemoto.GestioneProfilo.Control to javafx.fxml;
    exports com.example.GestioneRemoto.GestioneProfilo.Schermate;
    opens com.example.GestioneRemoto.GestioneProfilo.Schermate to javafx.fxml;





}