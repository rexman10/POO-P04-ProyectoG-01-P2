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

import com.mycompany.modelo.Auspiciante;
import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.Premio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


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

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        cbAuspiciante.getItems().setAll(Aplicacion.listaAuspiciantes);
    }




    public Premio guardarPremio() throws IOException {
        Premio nuevo = new Premio(Integer.valueOf(txtPuesto.getText()), txtDescripcion.getText(), cbAuspiciante.getValue());

        if (btGuardarPremio.isArmed()) {
            System.out.println(nuevo);
        }
        return nuevo;
    }

    @FXML
    public void cancelarPremio(){

    }
    
}
