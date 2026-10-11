package org.AutoFix.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.AutoFix.dao.ClienteDao;
import org.AutoFix.dao.impl.ClienteDaoImpl;
import org.AutoFix.model.Cliente;
import org.AutoFix.exceptions.DBException;

import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNit;
    @FXML private TableColumn<Cliente, String> colNombres;
    @FXML private TableColumn<Cliente, String> colApellidos;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colDireccion;

    @FXML private TextField txtNit;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;

    private ObservableList<Cliente> listaClientes;
    private ClienteDao clienteDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDao = new ClienteDaoImpl();
        
        colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colNit.setCellValueFactory(new PropertyValueFactory<>("nit"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        cargarDatosTabla();
    }

    public void cargarDatosTabla() {
        try {
            listaClientes = FXCollections.observableArrayList(clienteDao.listarClientes());
            tblClientes.setItems(listaClientes);
        } catch (DBException e) {
            mostrarAlerta("Error", "No se pudieron cargar los clientes: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void agregarCliente() {
        try {
            Cliente nuevo = new Cliente();
            nuevo.setNit(txtNit.getText());
            nuevo.setNombres(txtNombres.getText());
            nuevo.setApellidos(txtApellidos.getText());
            nuevo.setTelefono(txtTelefono.getText());
            nuevo.setDireccion(txtDireccion.getText());

            clienteDao.agregarCliente(nuevo);
            cargarDatosTabla();
            limpiarCampos();
            mostrarAlerta("Éxito", "Cliente agregado correctamente.", Alert.AlertType.INFORMATION);
        } catch (DBException e) {
            mostrarAlerta("Error", "No se pudo agregar el cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtNit.clear();
        txtNombres.clear();
        txtApellidos.clear();
        txtTelefono.clear();
        txtDireccion.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}