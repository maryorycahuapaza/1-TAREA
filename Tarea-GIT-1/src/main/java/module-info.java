module pe.edu.upeu.tareagit1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens pe.edu.upeu.tareagit1 to javafx.fxml;
    exports pe.edu.upeu.tareagit1;
}