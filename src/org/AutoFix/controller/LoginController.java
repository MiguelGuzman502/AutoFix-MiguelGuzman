package org.AutoFix.controller;

import org.AutoFix.dao.LoginDao;
import org.AutoFix.dao.impl.LoginDaoImpl;
import org.AutoFix.model.UsuarioSesion;
import org.AutoFix.util.SessionContext;
import org.AutoFix.exceptions.DBException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginController {
    private final LoginDao loginDao = new LoginDaoImpl();

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void handleLogin() {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            mostrarAlerta("Error", "Campos vacíos", "Por favor ingrese usuario y contraseña.", Alert.AlertType.WARNING);
            return;
        }

        try {
            UsuarioSesion sesion = loginDao.autenticar(user.trim(), pass);
            if (sesion != null) {
                SessionContext.iniciarSesion(sesion.getId(), sesion.getNombre(), sesion.getRol());
                cargarVentanaPrincipal();
            } else {
                mostrarAlerta("Acceso denegado", "Credenciales incorrectas", "Verifique su usuario y contraseña.", Alert.AlertType.ERROR);
            }
        } catch (DBException e) {
            mostrarAlerta("Error", "No se pudo iniciar sesión", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cargarVentanaPrincipal() {
        try {
            Stage stageActual = (Stage) txtUsuario.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org/AutoFix/view/PrincipalView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("AutoFix - Sistema de Gestión");
            stage.show();
            stageActual.close();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error de navegación", "No se pudo cargar la ventana principal.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String header, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}