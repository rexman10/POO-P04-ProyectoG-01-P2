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
    private TextField txtDireccion;
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
        llenarNuevoAuspiciante();
    }

    @FXML
    private void switchToAuspiciante(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarAuspiciantes");
    }

    @FXML
    public void llenarNuevoAuspiciante() {
        cbCiudad.getItems().setAll(Aplicacion.listaCiudades);
    }

    //@FXML
    //public void llenarCampos(Auspiciante a) {
       // lbTitulo.setText("Editar Mascota");
       // txtNombre.setText(a.getNombre());
        //dpFechaNacimiento.setValue(Fechas.calToLocalDate(c.getFechaInicioInscrip()));
        //cbCiudades.setValue(d.getCiudad());
        //txtEmail.setText(d.getEmail());
        //btGuardadDueño.setText("Editar");
        //btGuardadDueño.setOnAction(e -> edicionDueño(d));
   // }

    @FXML
    private void guardarNuevoAuspiciante() {
        ArrayList<Auspiciante> auspiciante = Aplicacion.listaAuspiciantes;//cargar la lista del archivo
        System.out.println("Guardando Auspiciante");
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        Ciudad c = cbCiudad.getValue();
        String email = txtEmail.getText();
        String webpage = txtWebpage.getText();
        Auspiciante temp = new Auspiciante(nombre,direccion,telefono,c,email,webpage);
        int id_comprobacion = temp.getCodigo();
        System.out.println("llegando a comprobacion");
        System.out.println(Aplicacion.auspicianteExiste(id_comprobacion));
        if (!Aplicacion.auspicianteExiste(id_comprobacion)) {
            System.out.println(Aplicacion.listaAuspiciantes);
            Aplicacion.listaAuspiciantes.add(temp);
            //System.out.println(Aplicacion.listaDueños);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/auspiciantes.csv",true))) {
                //id;nombre;tipo;raza;fecha_nac;foto;id_dueno
                bw.write(temp.getCodigo() + "," + temp.getNombre() + "," + temp.getDireccion() + "," + temp.getTelefono() + "," + temp.getCiudad() + "," + temp.getEmail() + "," + temp.getWebPage());
                bw.newLine();
                //mostrar informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Resultado de la operación");
                alert.setContentText("Nuevo Auspiciante agregado exitosamente");

                alert.showAndWait();
                Aplicacion.setRoot("AdministrarAuspiciantes");
            }
            catch (IOException e) {
                e.getMessage();
            }
        } 
    }

}
