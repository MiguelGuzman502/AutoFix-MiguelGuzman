package org.AutoFix.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    private static Stage escenarioPrincipal;

    @Override
    public void start(Stage primaryStage) {
        Main.escenarioPrincipal = primaryStage;
        Main.escenarioPrincipal.setTitle("AutoFix - Sistema de Gestión de Taller");
        invocarVista("LoginView.fxml");
        Main.escenarioPrincipal.show();
    }

    public static void invocarVista(String fxmlFile) {
        try {
            URL archivoFXML = Main.class.getResource("/org/AutoFix/view/" + fxmlFile);
            if (archivoFXML == null) {
                throw new RuntimeException("No se pudo encontrar el archivo FXML: " + fxmlFile);
            }
            FXMLLoader fxmlLoader = new FXMLLoader(archivoFXML);
            Parent root = fxmlLoader.load();
            Scene escena = new Scene(root);
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}