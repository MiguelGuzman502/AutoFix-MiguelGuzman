package org.AutoFix.controller;

import org.AutoFix.util.Conexion;
import org.AutoFix.exceptions.DBException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransaccionController {

    @FXML private TextField txtIdVehiculo;
    @FXML private TextField txtIdMecanico;
    @FXML private DatePicker dpFechaReparacion;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtCosto;

    @FXML
    public void registrarTransaccion() {
        String vehiculo = txtIdVehiculo.getText();
        String mecanico = txtIdMecanico.getText();
        LocalDate fecha = dpFechaReparacion.getValue();
        String descripcion = txtDescripcion.getText();
        String costo = txtCosto.getText();

        if (vehiculo.isEmpty() || mecanico.isEmpty() || fecha == null || descripcion.isEmpty() || costo.isEmpty()) {
            mostrarAlerta("Advertencia", "Campos incompletos", "Por favor llene todos los campos de la transacción.", Alert.AlertType.WARNING);
            return;
        }

        String sql = "{call sp_RegistrarReparacion(?, ?, ?, ?, ?)}";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement pstmt = conn.prepareCall(sql)) {
            
            pstmt.setInt(1, Integer.parseInt(vehiculo));
            pstmt.setInt(2, Integer.parseInt(mecanico));
            pstmt.setDate(3, java.sql.Date.valueOf(fecha));
            pstmt.setString(4, descripcion);
            pstmt.setDouble(5, Double.parseDouble(costo));
            
            pstmt.execute();
            mostrarAlerta("Éxito", "Transacción completada", "La orden de reparación se registró correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();

        } catch (SQLException e) {
            throw new DBException("Error al registrar la transacción en la base de datos", e);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Datos inválidos", "Verifique que los campos numéricos sean correctos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void limpiarCampos() {
        txtIdVehiculo.clear();
        txtIdMecanico.clear();
        dpFechaReparacion.setValue(null);
        txtDescripcion.clear();
        txtCosto.clear();
    }

    private void mostrarAlerta(String titulo, String header, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}