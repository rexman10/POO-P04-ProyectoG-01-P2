/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetalleMascotaController implements Initializable {

    @FXML
    private Label idLabTituloDetalleMascota;
    @FXML
    private Button idButtonregresarDetalleMascota;
    @FXML
    private Label idlblNombreMascota;
    @FXML
    private Label idLblFechaNacimiento;
    @FXML
    private Label idLblRaza;
    @FXML
    private Label idLblNombreDuenio;
    @FXML
    private ImageView idimagenMascota;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void regresarMenu(ActionEvent e) throws IOException {
        Aplicacion.setRoot("AdministrarMascotas");

    }


}
