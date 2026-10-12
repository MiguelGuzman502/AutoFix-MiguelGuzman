package org.AutoFix.controller;

import org.AutoFix.model.Mecanico;
import org.AutoFix.dao.MecanicoDao;
import org.AutoFix.dao.impl.MecanicoDaoImpl;
import org.AutoFix.exceptions.DBException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class MecanicoController implements Initializable {

    @FXML private TextField txtIdMecanico;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtTelefono;

    @FXML private TableView<Mecanico> tblMecanicos;
    @FXML private TableColumn<Mecanico, Integer> colIdMecanico;
    @FXML private TableColumn<Mecanico, String> colNombres;
    @FXML private TableColumn<Mecanico, String> colApellidos;
    @FXML private TableColumn<Mecanico, String> colTelefono;

    private ObservableList<Mecanico> listaMecanicos;
    private final MecanicoDao mecanicoDao = new MecanicoDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIdMecanico.setCellValueFactory(new PropertyValueFactory<>("idMecanico"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        cargarDatos();
    }

    public void cargarDatos() {
        try {
            listaMecanicos = FXCollections.observableArrayList(mecanicoDao.listarMecanicos());
            tblMecanicos.setItems(listaMecanicos);
        } catch (DBException e) {
            mostrarAlerta("Error", "No se pudieron cargar los mecánicos", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void guardarMecanico() {
        if (txtNombres.getText().isBlank() || txtApellidos.getText().isBlank() || txtTelefono.getText().isBlank()) {
            mostrarAlerta("Validación", "Datos incompletos", "Completa nombre, apellido y teléfono.", Alert.AlertType.WARNING);
            return;
        }
        try {
            mecanicoDao.agregarMecanico(new Mecanico(0, txtNombres.getText().trim(), txtApellidos.getText().trim(), txtTelefono.getText().trim()));
            cargarDatos(); limpiarCampos();
        } catch (DBException e) { mostrarAlerta("Error", "No se pudo guardar", e.getMessage(), Alert.AlertType.ERROR); }
    }

    @FXML
    public void editarMecanico() {
        Mecanico actual = tblMecanicos.getSelectionModel().getSelectedItem();
        if (actual == null) { mostrarAlerta("Aviso", "Selecciona un mecánico", "Elige una fila para editar.", Alert.AlertType.WARNING); return; }
        try {
            mecanicoDao.actualizarMecanico(new Mecanico(actual.getIdMecanico(), txtNombres.getText().trim(), txtApellidos.getText().trim(), txtTelefono.getText().trim()));
            cargarDatos(); limpiarCampos();
        } catch (DBException e) { mostrarAlerta("Error", "No se pudo editar", e.getMessage(), Alert.AlertType.ERROR); }
    }

    @FXML
    public void eliminarMecanico() {
        Mecanico actual = tblMecanicos.getSelectionModel().getSelectedItem();
        if (actual == null) { mostrarAlerta("Aviso", "Selecciona un mecánico", "Elige una fila para eliminar.", Alert.AlertType.WARNING); return; }
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar el mecánico seleccionado?", javafx.scene.control.ButtonType.YES, javafx.scene.control.ButtonType.NO);
        if (confirmar.showAndWait().orElse(javafx.scene.control.ButtonType.NO) != javafx.scene.control.ButtonType.YES) return;
        try { mecanicoDao.eliminarMecanico(actual.getIdMecanico()); cargarDatos(); limpiarCampos(); }
        catch (DBException e) { mostrarAlerta("Error", "No se pudo eliminar", e.getMessage(), Alert.AlertType.ERROR); }
    }

    @FXML
    public void seleccionarElemento() {
        Mecanico m = tblMecanicos.getSelectionModel().getSelectedItem();
        if (m != null) {
            txtIdMecanico.setText(String.valueOf(m.getIdMecanico()));
            txtNombres.setText(m.getNombres());
            txtApellidos.setText(m.getApellidos());
            txtTelefono.setText(m.getTelefono());
        }
    }

    @FXML
    public void limpiarCampos() {
        txtIdMecanico.clear();
        txtNombres.clear();
        txtApellidos.clear();
        txtTelefono.clear();
        tblMecanicos.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String header, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}