/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import com.mycompany.modelo.Auspiciante;
import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.Premio;

//import com.mycompany.modelo.Premio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author alex_
 */
public class CrearPremioController{

    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtPuesto;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private ComboBox<Auspiciante> cbAuspiciante;
    @FXML
    private Button btGuardarPremio;
    @FXML
    private Button btCancelar;
    public static ArrayList<Premio> premios = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        cbAuspiciante.getItems().setAll(Aplicacion.listaAuspiciantes);
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) lblTitulo.getScene().getWindow();
        stage.close();
    }


    public void guardarPremio() throws IOException {

        System.out.println("nuevo premio");
        //
        //COMPLETAR
        if (txtPuesto.getText().equals("")) {
            Aplicacion.mostrarAlerta(Alert.AlertType.ERROR, "No ha especificado ningun puesto");
        }
        if (txtDescripcion.getText().equals("")) {
            Aplicacion.mostrarAlerta(Alert.AlertType.ERROR, "No ha especificado ningun premio");
        } 
        try {
            System.out.println("se crea objeto premio");
            Premio nuevo = new Premio(Integer.valueOf(txtPuesto.getText()), txtDescripcion.getText(), cbAuspiciante.getValue());
            premios.add(nuevo);
            cerrarVentana();
            System.out.println(nuevo);
            System.out.println(premios);
        } catch (NumberFormatException e) {
            Aplicacion.mostrarAlerta(Alert.AlertType.ERROR, "Debe ingresar un valor para el stock");
        }
        }

    @FXML
    public void cancelarPremio(){
        cerrarVentana();
    }
    
}
