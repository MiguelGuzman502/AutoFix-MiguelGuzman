package org.AutoFix.controller;

import org.AutoFix.util.SessionContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PrincipalController {

    @FXML
    private Label lblSesion;
    @FXML
    private Button btnMecanicos;
    @FXML
    private Button btnReparaciones;
    @FXML
    private Button btnAprobarCalidad;
    @FXML
    public void initialize() {

        SessionContext sesion = SessionContext.getInstancia();

        if (sesion == null) {
            lblSesion.setText("Sin sesión");
            btnMecanicos.setDisable(true);
            btnReparaciones.setDisable(true);
            btnAprobarCalidad.setDisable(true);
            return;
        }

        lblSesion.setText(
                "Usuario: " + sesion.getUsuario()
                + " | Rol: " + sesion.getRol()
        );

        btnMecanicos.setDisable(
                !sesion.tieneRol("Jefe de Taller")
        );

        btnReparaciones.setDisable(
        !(sesion.tieneRol("Jefe de Taller")
        || sesion.tieneRol("Mecanico"))
        );

        btnAprobarCalidad.setDisable(
                !sesion.tieneRol("Jefe de Taller")
        );
    }

    @FXML
    private void abrirMecanicos() {
        abrir("MecanicoView.fxml");
    }

    @FXML
    private void abrirReparaciones() {
        abrir("TransaccionView.fxml");
    }

    @FXML
    private void abrirAprobacionCalidad() {
        abrir("AprobacionCalidadView.fxml");
    }

    private void abrir(String vista) {

        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource(
                            "/org/AutoFix/view/" + vista
                    )
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("AutoFix - " + vista);
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("No se pudo abrir la vista");
            alerta.setContentText(
                    "Revisa el apartado Output de NetBeans."
            );
            alerta.showAndWait();
        }
    }

    @FXML
    private void cerrarSesion() {

        SessionContext.cerrarSesion();

        try {
            Stage stage = (Stage)
                    lblSesion.getScene().getWindow();

            Parent root = FXMLLoader.load(
                    getClass().getResource(
                            "/org/AutoFix/view/LoginView.fxml"
                    )
            );

            stage.setScene(new Scene(root));

        } catch (Exception e) {

            e.printStackTrace();

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText(
                    "No se pudo cerrar sesión."
            );
            alerta.showAndWait();
        }
    }
}