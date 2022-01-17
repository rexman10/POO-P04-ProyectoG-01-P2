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
import com.mycompany.modelo.Fechas;
import com.mycompany.modelo.Auspiciante;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class CrearAuspicianteController {

    @FXML
    private Label lbTitulo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ComboBox<Ciudad> cbCiudad;
    //falta el de buscar la ruta revisar las clases
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtWebpage;
    @FXML
    private Button cancelButtonAuspiciante;
    /**
     * Initializes the controller class.
     */
   
    @FXML
    private void initialize() {
       // llenarNuevaMascota();
    }

    @FXML
    private void switchToAuspiciante(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarAuspiciante");
    }

    @FXML
    public void llenarNuevaMascota() {
        cbCiudad.getItems().setAll(Aplicacion.listaCiudades);
    }

    @FXML
    public void llenarCampos(Auspiciante a) {
        //lbTitulo.setText("Editar Mascota");
       // txtNombre.setText(a.getNombre());
        //if (a.getTipoMascota().equals("Perro"))
         //   rbPerro.setSelected(true);
        //else
         //   rbGato.setSelected(true);
        //dpFechaNacimiento.setValue(Fechas.calToLocalDate(c.getFechaInicioInscrip()));
        //cbCiudades.setValue(d.getCiudad());
        //txtEmail.setText(d.getEmail());
        //btGuardadDueño.setText("Editar");
        //btGuardadDueño.setOnAction(e -> edicionDueño(d));
    }

   // @FXML
  //  private void guardarNuevaMascota() {
//        ArrayList<Mascota> mascotas = Aplicacion.listaMascotas;//cargar la lista del archivo
//        System.out.println("Guardando mascota");
//        String nombre = txtNombre.getText();
//        RadioButton selectedRadioButton = (RadioButton) tipo.getSelectedToggle();
//        String tipo = selectedRadioButton.getText();
 //       String fecha_dp = dpFechaNacimiento.getValue().toString();
//        String raza = txtRaza.getText();
//        Dueño d = cbDueños.getValue();
//        Mascota temp = new Mascota(nombre,tipo,raza,fecha_dp,"",d.getCodigo());
 //       int id_comprobacion = temp.getCodigo();
//        System.out.println("llegando a comprobacion");
//        System.out.println(Aplicacion.mascotaExiste(id_comprobacion));
//        if (!Aplicacion.mascotaExiste(id_comprobacion)) {
//            System.out.println(Aplicacion.listaMascotas);
 //           Aplicacion.listaMascotas.add(temp);
            //System.out.println(Aplicacion.listaDueños);
 //           try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/mascotas.csv",true))) {
                //id;nombre;tipo;raza;fecha_nac;foto;id_dueno
 //               bw.write(temp.getCodigo() + ";" + temp.getTipoMascota() + ";" + temp.getRaza() + ";" + temp.getFechaNacimiento() + ";" + temp.getUrlFoto() + ";" + temp.getIdDueño());
 //               bw.newLine();
                //mostrar informacion
  //              Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Information Dialog");
  //              alert.setHeaderText("Resultado de la operación");
  //              alert.setContentText("Nueva mascota agregado exitosamente");

  //              alert.showAndWait();
  //              Aplicacion.setRoot("AdministrarDueños");
 //           }
  //          catch (IOException e) {
  //              e.getMessage();
  //          }
  //      } 
  //  }

}
