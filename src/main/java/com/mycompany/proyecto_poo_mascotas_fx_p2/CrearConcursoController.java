/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author alex_
 */
public class CrearConcursoController {

    @FXML
    private Button cancelButtonConcurso;

    /**
     * Initializes the controller class.
     */
 
    @FXML
    private void switchToConcurso(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarConcursos");
    }

    

    
}
