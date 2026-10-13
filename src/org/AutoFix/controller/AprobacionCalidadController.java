package org.AutoFix.controller;

import org.AutoFix.dao.OrdenTrabajoDao;
import org.AutoFix.dao.impl.OrdenTrabajoDaoImpl;
import org.AutoFix.exceptions.DBException;
import org.AutoFix.util.SessionContext;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AprobacionCalidadController {

    @FXML
    private TextField txtIdOrden;

    private OrdenTrabajoDao ordenDao = new OrdenTrabajoDaoImpl();

    @FXML
    private void aprobarCalidad() {

        try {

            if (txtIdOrden.getText().trim().isEmpty()) {
                mostrarMensaje("Ingrese el ID de la orden.");
                return;
            }

            int idOrden = Integer.parseInt(
                    txtIdOrden.getText().trim()
            );

            if (idOrden <= 0) {
                mostrarMensaje("Ingrese un ID válido.");
                return;
            }

            SessionContext sesion = SessionContext.getInstancia();

            if (sesion == null
                    || !sesion.tieneRol("Jefe de Taller")) {

                mostrarMensaje(
                        "Solo el Jefe de Taller puede aprobar reparaciones."
                );
                return;
            }

            int idJefe = sesion.getIdUsuario();

            if (idJefe <= 0) {
                mostrarMensaje(
                        "No se encontró el ID del usuario. Inicie sesión nuevamente."
                );
                return;
            }

            ordenDao.aprobarCalidad(idOrden, idJefe);

            mostrarMensaje("Reparación aprobada correctamente.");

            txtIdOrden.clear();

        } catch (NumberFormatException e) {

            mostrarMensaje("El ID debe ser un número entero.");

        } catch (DBException e) {

            mostrarMensaje(
                    "No se pudo aprobar la reparación: " + e.getMessage()
            );
        }
    }

    @FXML
    private void cerrarVentana() {

        Stage stage = (Stage) txtIdOrden.getScene().getWindow();
        stage.close();
    }

    private void mostrarMensaje(String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("AutoFix");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}