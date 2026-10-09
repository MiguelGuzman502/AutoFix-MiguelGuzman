package org.AutoFix.controller;

import org.AutoFix.util.Conexion;
import org.AutoFix.exceptions.DBException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

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

        if (validarCredenciales(user, pass)) {
            mostrarAlerta("Éxito", "Acceso concedido", "Bienvenido al sistema AutoFix.", Alert.AlertType.INFORMATION);
            cargarVentanaPrincipal();
        } else {
            mostrarAlerta("Acceso denegado", "Credenciales incorrectas", "Verifique su usuario y contraseña.", Alert.AlertType.ERROR);
        }
    }

    private boolean validarCredenciales(String usuario, String password) {
        String sql = "call sp_login(?, ?)";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement pstmt = conn.prepareCall(sql)) {
            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Si devuelve un registro, las credenciales son válidas
            }
        } catch (SQLException e) {
            throw new DBException("Error al validar las credenciales de acceso", e);
        }
    }

    private void cargarVentanaPrincipal() {
        try {
            Stage stageActual = (Stage) txtUsuario.getScene().getWindow();
            stageActual.close();

            Parent root = FXMLLoader.load(getClass().getResource("/org/AutoFix/view/PrincipalView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("AutoFix - Sistema de Gestión");
            stage.show();
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