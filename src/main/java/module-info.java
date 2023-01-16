module com.example.progetto2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires java.sql;



    opens com.example.progetto2 to javafx.fxml;
    exports com.example.progetto2;
}