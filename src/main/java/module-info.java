module com.example.progetto2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires java.sql;



    opens com.example.progetto2 to javafx.fxml;
    exports com.example.progetto2;
    opens com.example.progetto2.Schermate to javafx.fxml;
    exports com.example.progetto2.Schermate;
    opens com.example.progetto2.Control to javafx.fxml;
    exports com.example.progetto2.Control;
}