/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import com.mycompany.modelo.Mascota;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    
    public void llenarCamposDetMasc(Mascota m){
        idlblNombreMascota.setText(m.getNombre());
        idLblRaza.setText(m.getRaza());
        idLblNombreDuenio.setText(m.getDuenio().getNombre());
        //idimagenMascota.setImage(new Image("archivos/Imagenesmascotas/"+(m.getUrlFoto())));
        idLblFechaNacimiento.setText(m.getFechaNacimiento());
         }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void regresarMenu(ActionEvent e) throws IOException {
        Aplicacion.setRoot("AdministrarMascotas");

    }


}
