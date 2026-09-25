module pe.edu.upeu.evaluacion01_proyectocineplanet {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires static lombok;
    requires jakarta.validation;
    opens pe.edu.upeu.evaluacion01_proyectocineplanet.controller to javafx.fxml;
    opens pe.edu.upeu.evaluacion01_proyectocineplanet to javafx.fxml;
    opens pe.edu.upeu.evaluacion01_proyectocineplanet.model;
    exports pe.edu.upeu.evaluacion01_proyectocineplanet;
    exports pe.edu.upeu.evaluacion01_proyectocineplanet.model;
}