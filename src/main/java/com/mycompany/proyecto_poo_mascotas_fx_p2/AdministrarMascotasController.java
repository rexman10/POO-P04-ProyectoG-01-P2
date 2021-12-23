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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author alex_
 */
public class AdministrarMascotasController {


    
    @FXML
    private void switchToCrearMascota(ActionEvent event) throws IOException {
        Aplicacion.setRoot("crearMascota");
    }
    
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        Aplicacion.setRoot("principalMenu");
    }
}
