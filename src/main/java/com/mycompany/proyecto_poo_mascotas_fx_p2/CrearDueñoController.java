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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Dueño;
/**
 * FXML Controller class
 *
 * @author alex_
 */
public class CrearDueñoController {


    @FXML
    private Button cancelButtonDueño;
    @FXML
    private Button btGuardadDueño;
    @FXML
    private Label lbTitulo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<Ciudad> cbCiudades;
   
    @FXML
    private void initialize() {
        llenarNuevoDueño();
    }

    @FXML
    private void switchToDueño(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarDueños");
    }

        @FXML
    public void llenarNuevoDueño() {
        cbCiudades.getItems().setAll(Aplicacion.listaCiudades);
    }

    @FXML
    public void llenarCampos(Dueño d) {
        lbTitulo.setText("Editar Dueño");
        txtNombre.setText(d.getNombre());
        txtApellidos.setText(d.getApellidos());
        txtDireccion.setText(d.getDireccion());
        txtTelefono.setText(d.getTelefono());
        cbCiudades.setValue(d.getCiudad());
        txtEmail.setText(d.getEmail());
        btGuardadDueño.setText("Editar");
        btGuardadDueño.setOnAction(e -> edicionDueño(d));
    }

    @FXML
    private void guardarNuevoDueño() {
        ArrayList<Dueño> dueños = Aplicacion.listaDueños;//cargar la lista del archivo
        System.out.println("Guardando dueño");
        String nombre = txtNombre.getText().toUpperCase();
        String apellido = txtApellidos.getText().toUpperCase();
        String direcc = txtDireccion.getText();
        String tel = txtTelefono.getText();
        Ciudad city = cbCiudades.getValue();
        String mail = txtEmail.getText();
        Dueño temp = new Dueño(nombre,apellido,direcc,tel,city,mail);
        int id_comprobacion = temp.getCodigo();
        System.out.println("llegando a comprobacion");
        System.out.println(Aplicacion.dueñoExiste(id_comprobacion));
        if (!Aplicacion.dueñoExiste(id_comprobacion)) {
            System.out.println(Aplicacion.listaDueños);
            Aplicacion.listaDueños.add(temp);
            //System.out.println(Aplicacion.listaDueños);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/duenosP4.csv",true))) {
                //id,apellidos,nombres,direccion,telefono,ciudad,email
                bw.write(temp.getCodigo() + "," + temp.getApellidos().toUpperCase() + "," + temp.getNombre().toUpperCase() + "," + temp.getDireccion().toUpperCase() + "," + temp.getTelefono() + "," + temp.getCiudad() + "," + temp.getEmail());
                bw.newLine();
                //mostrar informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Resultado de la operación");
                alert.setContentText("Nuevo dueño agregado exitosamente");

                alert.showAndWait();
                Aplicacion.setRoot("AdministrarDueños");
            }
            catch (IOException e) {
                e.getMessage();
            }
        } 
    }

    @FXML
    public void edicionDueño(Dueño d) {
        d.setNombres(txtNombre.getText());
        d.setApellidos(txtApellidos.getText());
        if (btGuardadDueño.isArmed()) {
            System.out.println("entra al if");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/duenosP4.csv"))) {
                for (Dueño dueño : Aplicacion.listaDueños) {
                    //id,apellidos,nombres,direccion,telefono,ciudad,email
                    bw.write(dueño.getCodigo() + "," + dueño.getApellidos() + "," + dueño.getNombre() + "," + dueño.getDireccion() + "," + dueño.getTelefono() + "," + dueño.getCiudad() + "," + dueño.getEmail());
                    bw.newLine();
                }
                //mostrar informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Resultado de la operación");
                alert.setContentText("Dueño editado exitosamente");
                alert.showAndWait();
                Aplicacion.setRoot("AdministrarDueños");
            }
            catch (IOException e) {
                e.getMessage();
            }
        }
    }


}
