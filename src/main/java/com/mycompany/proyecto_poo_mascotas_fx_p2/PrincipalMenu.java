package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrincipalMenu {

    @FXML
    private void switchToConcursos(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarConcursos");
    }
    
    @FXML
    private void switchToMascotas(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarMascotas");
    }
    
    //@FXML
    //private void switchToDueños(ActionEvent event) throws IOException {
    //    Aplicacion.setRoot("AdministrarDueños");
    //}
    
}
