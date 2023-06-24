module com.example.serverclienttt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.serverclienttt to javafx.fxml;
    exports com.example.serverclienttt;
}