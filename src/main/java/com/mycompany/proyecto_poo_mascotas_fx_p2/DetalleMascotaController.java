/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import com.mycompany.modelo.Mascota;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

import com.mycompany.modelo.Mascota;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetalleMascotaController{

    @FXML
    private Label lbTitulo;
    @FXML
    private Button btRegresar;
    @FXML
    private Label lbNombreMascota;
    @FXML
    private Label lbFechaNacimiento;
    @FXML
    private Label lbRaza;
    @FXML
    private Label lbNombreDueño;
    @FXML
    private ImageView ivMascota;

    public static ArrayList<Mascota> lista_temp = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        llenarCampos();
    }

    @FXML
    public void regresarMenu(ActionEvent e) throws IOException {
        cerrarVentana();
        lista_temp.clear();

    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) lbTitulo.getScene().getWindow();
        stage.close();
    }

    public void llenarCampos() {
        Mascota m = lista_temp.get(0);
        System.out.println(m);
        lbNombreMascota.setText(m.getNombre());
        lbFechaNacimiento.setText(m.getFechaNacimiento());
        lbRaza.setText(m.getRaza());
        lbNombreDueño.setText(m.getDuenio().getCredenciales());
        InputStream input = null;
        System.out.println("entrando al try");
        try {
            System.out.println("entro");
            String fileName = "ImagenesMascotas/" + m.getUrlFoto();//armar la ruta de la foto
            System.out.println(fileName);
            input = Aplicacion.class.getResource(fileName).openStream();
            //crear la imagen 
            Image image = new Image(input, 100, 100, false, false);
            ivMascota.setImage(image);
            System.out.println("crea la img");
        } catch (Exception ex) {
            ex.getMessage();
        }

    }


}
