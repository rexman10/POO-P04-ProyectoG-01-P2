/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class AdministrarConcursosController {


    @FXML
    private VBox primary;
    @FXML
    private TableView<?> tvConcursos;
    @FXML
    private Button enviarInv;

    @FXML
    private void switchToCrearConcurso(ActionEvent event) throws IOException {
        Aplicacion.setRoot("crearConcurso");
    }
    
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        Aplicacion.setRoot("principalMenu");
    }
}
