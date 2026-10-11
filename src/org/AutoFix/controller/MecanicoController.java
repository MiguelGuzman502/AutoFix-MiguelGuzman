package org.AutoFix.controller;

import org.AutoFix.model.Mecanico;
import org.AutoFix.util.Conexion;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIdMecanico.setCellValueFactory(new PropertyValueFactory<>("idMecanico"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        cargarDatos();
    }

    public void cargarDatos() {
        listaMecanicos = FXCollections.observableArrayList();
        String sql = "SELECT idMecanico, nombres, apellidos, telefono FROM Mecanico";
        
        try (Connection conn = Conexion.getInstancia().getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                listaMecanicos.add(new Mecanico(
                    rs.getInt("idMecanico"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono")
                ));
            }
            tblMecanicos.setItems(listaMecanicos);
        } catch (SQLException e) {
            throw new DBException("Error al cargar los datos de mecánicos", e);
        }
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