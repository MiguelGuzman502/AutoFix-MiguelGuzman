package org.AutoFix.controller;

import org.AutoFix.dao.TransaccionDao;
import org.AutoFix.dao.impl.TransaccionDaoImpl;
import org.AutoFix.exceptions.DBException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class TransaccionController {

    private final TransaccionDao transaccionDao =
            new TransaccionDaoImpl();

    @FXML private TextField txtIdVehiculo;
    @FXML private TextField txtIdMecanico;
    @FXML private DatePicker dpFechaReparacion;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtCosto;
    @FXML private TextField txtKilometraje;
    @FXML
    public void registrarTransaccion() {

        String vehiculo = txtIdVehiculo.getText();
        String mecanico = txtIdMecanico.getText();
        LocalDate fecha = dpFechaReparacion.getValue();
        String descripcion = txtDescripcion.getText();
        String costo = txtCosto.getText();
        String kilometraje = txtKilometraje.getText();

        if (vehiculo.isBlank() || mecanico.isBlank()
                || fecha == null || descripcion.isBlank()
                || costo.isBlank() || kilometraje.isBlank()) {

            mostrarAlerta("Advertencia", "Campos incompletos",
                    "Por favor llene todos los campos.",
                    Alert.AlertType.WARNING);
            return;
        }

        try {
            int idVehiculo = Integer.parseInt(vehiculo.trim());
            int idMecanico = Integer.parseInt(mecanico.trim());

            double costoNumerico =
                    Double.parseDouble(costo.trim());

            int kilometrajeNumerico =
                    Integer.parseInt(kilometraje.trim());

            if (idVehiculo <= 0 || idMecanico <= 0
                    || costoNumerico < 0
                    || kilometrajeNumerico < 0
                    || !Double.isFinite(costoNumerico)) {

                mostrarAlerta("Error", "Datos inválidos",
                        "Verifique los IDs, costo y kilometraje.",
                        Alert.AlertType.WARNING);
                return;
            }

            transaccionDao.registrarReparacion(
                    idVehiculo,
                    idMecanico,
                    fecha,
                    descripcion.trim(),
                    costoNumerico,
                    kilometrajeNumerico
            );

            mostrarAlerta("Éxito", "Transacción completada",
                    "La reparación se registró correctamente.",
                    Alert.AlertType.INFORMATION);

            limpiarCampos();

        } catch (DBException e) {

            mostrarAlerta("Error", "Error de base de datos",
                    e.getMessage(), Alert.AlertType.ERROR);

        } catch (NumberFormatException e) {

            mostrarAlerta("Error", "Datos inválidos",
                    "Verifique que los números sean correctos.",
                    Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void limpiarCampos() {

        txtIdVehiculo.clear();
        txtIdMecanico.clear();
        dpFechaReparacion.setValue(null);
        txtDescripcion.clear();
        txtCosto.clear();
        txtKilometraje.clear();
    }
    private void mostrarAlerta(
            String titulo,
            String header,
            String contenido,
            Alert.AlertType tipo) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
