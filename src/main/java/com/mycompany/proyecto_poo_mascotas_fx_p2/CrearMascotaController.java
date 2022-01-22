/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Dueño;
import com.mycompany.modelo.Fechas;
import com.mycompany.modelo.Mascota;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class CrearMascotaController {

    @FXML
    private Label lbTitulo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField idBuscadorMascota;
    @FXML
    private RadioButton rbPerro;
    @FXML
    private RadioButton rbGato;
    @FXML
    ToggleGroup tipo;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextField txtRaza;
    @FXML
    private ComboBox<Dueño> cbDueños;
    //falta el de buscar la ruta revisar las clases
    @FXML
    private Button btcancelarMAscota;
    @FXML
    private Button btGuardarMascota;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize() {
        llenarNuevaMascota();
    }

    @FXML
    private void switchToMascota(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarMascotas");
    }

    @FXML
    public void llenarNuevaMascota() {
        cbDueños.getItems().setAll(Aplicacion.listaDueños);
    }

    @FXML
    public void llenarCampos(Mascota m) {
        lbTitulo.setText("Editar Mascota");
        txtNombre.setText(m.getNombre());
        txtRaza.setText(m.getRaza());
        idBuscadorMascota.setPromptText(m.getUrlFoto());
        cbDueños.setValue(m.getDuenio());
        dpFechaNacimiento.setValue(LocalDate.parse(m.getFechaNacimiento()));
        btGuardarMascota.setText("Guardar");
        btGuardarMascota.setOnAction(e -> edicionMascota(m));
        if (m.getTipoMascota().equals("Perro")) {
            rbPerro.setSelected(true);
        } else {
            rbGato.setSelected(true);
        }


    }

    @FXML

    private void guardarNuevaMascota() {
        ArrayList<Mascota> mascotas = Aplicacion.listaMascotas;//cargar la lista del archivo
        System.out.println("Guardando mascota");
        String nombre = txtNombre.getText();
        RadioButton selectedRadioButton = (RadioButton) tipo.getSelectedToggle();
        String tipo = selectedRadioButton.getText();
        String fecha_dp = dpFechaNacimiento.getValue().toString();
        String raza = txtRaza.getText();
        Dueño d = cbDueños.getValue();
        System.out.println("==");
        System.out.println(d);
        System.out.println(d.getCodigo());
        System.out.println(d.getCredenciales());
        System.out.println("==");
        Mascota temp = new Mascota(nombre, tipo, raza, fecha_dp, "", d.getCodigo());
        int id_comprobacion = temp.getCodigo();
        System.out.println("llegando a comprobacion");
        System.out.println(Aplicacion.mascotaExiste(id_comprobacion));
        if (!Aplicacion.mascotaExiste(id_comprobacion)) {
            System.out.println(Aplicacion.listaMascotas);
            Aplicacion.listaMascotas.add(temp);
            temp.setDuenio(d);
            temp.setUrlFoto("incognito.jpg");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/mascotas.csv", true))) {
                
                bw.write(temp.getCodigo() + ";" + temp.getNombre() + ";" + temp.getTipoMascota() + ";" + temp.getRaza() + ";" + temp.getFechaNacimiento() + ";" + temp.getUrlFoto() + ";" + temp.getId_dueño());
                bw.newLine();
                //mostrar informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Resultado de la operación");
                alert.setContentText("Nueva mascota agregado exitosamente");
                
                //llenarNuevaMascota();
                
                alert.showAndWait();
                Aplicacion.setRoot("AdministrarMascotas");
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    @FXML
    public void edicionMascota(Mascota m) {
        m.setNombre(txtNombre.getText());
        
        if (btGuardarMascota.isArmed()) {
            System.out.println("entra al if");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/mascotas.csv"))) {
                bw.write("id,nombre,tipo,raza,fecha_nac,foto,id_dueno");
                bw.newLine();
                for (Mascota mascota : Aplicacion.listaMascotas) {
                bw.write(mascota.getCodigo() + ";" + mascota.getNombre() + ";" + mascota.getTipoMascota()+ ";" + mascota.getRaza()+ ";" + mascota.getFechaNacimiento()+ ";" + mascota.getUrlFoto() + ";" + mascota.getDuenio().getCodigo());
                    bw.newLine();
                }
                //mostrar informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Resultado de la operación");
                alert.setContentText("Mascota editado exitosamente");
                alert.showAndWait();
                Aplicacion.setRoot("AdministrarMascotas");
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
