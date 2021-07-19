module start.projetojava {
    requires javafx.controls;
    requires javafx.fxml;

    opens start.projetojava to javafx.fxml;
    exports start.projetojava;
}
