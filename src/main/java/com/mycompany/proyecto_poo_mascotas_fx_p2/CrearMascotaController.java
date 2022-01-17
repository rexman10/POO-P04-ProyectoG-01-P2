/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;

import com.mycompany.modelo.Mascota;
import com.mycompany.modelo.Dueño;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class CrearMascotaController {
    
    @FXML
    private TextField txtNombre;
    @FXML
    private RadioButton rbAnimal;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private TextField txtRaza;
    @FXML
    private ComboBox<Dueño> cbDuenio;
    @FXML
    private TextField txtfoto;
    @FXML
    private TextField tfHora;
    
    @FXML
    private Button cancelButtonMascota;

    @FXML
    private void initialize(){
        llenarNuevaMascota();
    }
    
    @FXML
    private void llenarNuevaMascota(){
        cbDuenio.getItems().setAll(Aplicacion.listaDueños);
    }
    
    @FXML
    private void CrearNuevaMascota(){
        ArrayList<Mascota> mascotas = Aplicacion.listaMascotas;
        String nombre = txtNombre.getText().toUpperCase();
        String tipo = rbAnimal.getTypeSelector();
        String[] fecha_dp = dpFecha.getValue().toString().split("-");
        Calendar fecha_new = new GregorianCalendar(Integer.parseInt(fecha_dp[0]),Integer.parseInt(fecha_dp[1]),Integer.parseInt(fecha_dp[2]));
        String hora = tfHora.getText();
        String raza = txtRaza.getText().toUpperCase();
        Dueño duenio = cbDuenio.getValue();
        String foto = txtfoto.getText().toUpperCase();
    }
    
   
    
    @FXML
    private void switchToMascota(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarMascotas");
    }

}
