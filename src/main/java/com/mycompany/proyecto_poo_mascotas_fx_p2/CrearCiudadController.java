/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.mycompany.modelo.Auspiciante;
import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.FechasUtil;
import com.mycompany.modelo.Premio;


/**
 * FXML Controller class
 *
 * @author alex_
 */
public class CrearCiudadController {

    @FXML
    private Button btCancelarCiudad;
    @FXML
    private Button btGuardarCiudad;
    @FXML 
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cbProvincia;
   
    @FXML
    private Label lblCabecera;




    @FXML
    private void initialize() {
        llenarNuevaCiudad();
    }
 
    @FXML
    private void switchToCiudad(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarCiudades");
    }

    @FXML
    public void llenarNuevaCiudad () {
        ArrayList<String> a = new ArrayList<>();
        a.add("Azuay");
        a.add("Bolivar");
        a.add("Cañar");
        a.add("Carchi");
        a.add("Chimborazo");
        a.add("Cotopaxi");
        a.add("El Oro");
        a.add("Esmeraldas");
        a.add("Galapagos");
        a.add("Guayas");
        a.add("Imbabura");
        a.add("Loja");
        a.add("Los Rios");
        a.add("Manabi");
        a.add("Morona Santiago");
        a.add("Napo");
        a.add("Orellana");
        a.add("Pastaza");
        a.add("Pichincha");
        a.add("Santa Elena");
        a.add("Santo Domingo de los Tsachilas");
        a.add("Sucumbios");
        a.add("Tungurahua");
        a.add("Zamora Chinchipe");
        cbProvincia.getItems().setAll(a);
    }


    @FXML
    public void llenarCampos(Ciudad c) {
        lblCabecera.setText("Editar Ciudad");
        btGuardarCiudad.setText("Editar");
        btGuardarCiudad.setOnAction(e -> edicionCiudad(c));
        cbProvincia.setValue(c.getProvincia());
        txtNombre.setText(c.getNombre());
    }
    
    @FXML
    private void guardarNuevaCiudad() {
        ArrayList<Ciudad> ciudades = Aplicacion.listaCiudades;//cargar la lista del archivo
        System.out.println("Guardando ciudad");
        String nombre = txtNombre.getText();
        String prov_new = cbProvincia.getValue();
        Ciudad temp = new Ciudad(nombre,prov_new);
        int id_comprobacion = temp.getCodigo();
        System.out.println("llegando a comprobacion");
        System.out.println(Aplicacion.ciudadExiste(id_comprobacion));
        if (!Aplicacion.ciudadExiste(id_comprobacion)) {
            System.out.println(Aplicacion.listaCiudades);
            Aplicacion.listaCiudades.add(temp);
            System.out.println(Aplicacion.listaCiudades);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/ciudades.csv",true))) {
                bw.write(temp.getCodigo() + "," + temp.getNombre() + "," + temp.getProvincia());
                bw.newLine();
                //mostrar informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Resultado de la operación");
                alert.setContentText("Nueva ciudad agregado exitosamente");

                alert.showAndWait();
                Aplicacion.setRoot("AdministrarCiudades");
            }
            catch (IOException e) {
                e.getMessage();
            }
        } 
    }

    @FXML
    public void edicionCiudad(Ciudad c) {
        c.setNombre(txtNombre.getText());
        c.setProvincia(cbProvincia.getValue());
        if (btGuardarCiudad.isArmed()) {
            System.out.println("entra al if");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/ciudades.csv"))) {
                for (Ciudad ciudad : Aplicacion.listaCiudades) {
                    bw.write(ciudad.getCodigo() + "," + ciudad.getNombre() + "," + ciudad.getProvincia());
                    bw.newLine();
                }
                //mostrar informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Resultado de la operación");
                alert.setContentText("Ciudad editada exitosamente");
                alert.showAndWait();
                Aplicacion.setRoot("AdministrarCiudades");
            }
            catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
