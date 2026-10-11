package org.AutoFix.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.AutoFix.dao.VehiculoDao;
import org.AutoFix.dao.impl.VehiculoDaoImpl;
import org.AutoFix.model.Vehiculo;
import org.AutoFix.exceptions.DBException;

import java.net.URL;
import java.util.ResourceBundle;

public class VehiculoController implements Initializable {

    @FXML private TableView<Vehiculo> tblVehiculos;
    @FXML private TableColumn<Vehiculo, Integer> colIdVehiculo;
    @FXML private TableColumn<Vehiculo, Integer> colIdCliente;
    @FXML private TableColumn<Vehiculo, String> colMarca;
    @FXML private TableColumn<Vehiculo, String> colModelo;
    @FXML private TableColumn<Vehiculo, Integer> colAnio;
    @FXML private TableColumn<Vehiculo, String> colPlaca;

    @FXML private TextField txtIdCliente;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtAnio;
    @FXML private TextField txtPlaca;

    private ObservableList<Vehiculo> listaVehiculos;
    private VehiculoDao vehiculoDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculoDao = new VehiculoDaoImpl();
        
        colIdVehiculo.setCellValueFactory(new PropertyValueFactory<>("idVehiculo"));
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

        cargarDatosTabla();
    }

    public void cargarDatosTabla() {
        try {
            listaVehiculos = FXCollections.observableArrayList(vehiculoDao.listarVehiculos());
            tblVehiculos.setItems(listaVehiculos);
        } catch (DBException e) {
            mostrarAlerta("Error", "No se pudieron cargar los vehículos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void agregarVehiculo() {
        try {
            Vehiculo nuevo = new Vehiculo();
            nuevo.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
            nuevo.setMarca(txtMarca.getText());
            nuevo.setModelo(txtModelo.getText());
            nuevo.setAnio(Integer.parseInt(txtAnio.getText()));
            nuevo.setPlaca(txtPlaca.getText());

            vehiculoDao.agregarVehiculo(nuevo);
            cargarDatosTabla();
            limpiarCampos();
            mostrarAlerta("Éxito", "Vehículo agregado correctamente.", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "El ID del cliente y el año deben ser numéricos.", Alert.AlertType.WARNING);
        } catch (DBException e) {
            mostrarAlerta("Error", "No se pudo agregar el vehículo: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtIdCliente.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtAnio.clear();
        txtPlaca.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}