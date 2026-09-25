package pe.edu.upeu.evaluacion01_proyectocineplanet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pe.edu.upeu.evaluacion01_proyectocineplanet.config.AppContext;

import java.io.IOException;

public class Cineplanet extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppContext appContext= AppContext.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(Cineplanet.class.getResource("/view/cineplanet.fxml"));
        fxmlLoader.setControllerFactory(appContext::getBean);
        Screen screen= Screen.getPrimary();
        Rectangle2D rectangle2D=screen.getVisualBounds();
        Scene scene = new Scene(fxmlLoader.load(), rectangle2D.getWidth(), rectangle2D.getHeight());
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setTitle("Main Pelicula");
        stage.setScene(scene);
        stage.show();
    }
}
