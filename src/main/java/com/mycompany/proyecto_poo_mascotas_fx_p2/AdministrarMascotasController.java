/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import com.mycompany.modelo.Mascota;
import com.mycompany.modelo.Dueño;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class AdministrarMascotasController {
    
    @FXML
    private Label lvTitulo;
    @FXML
    private TableView<Mascota> tvMascotas;
    @FXML
    private TableColumn<Mascota, Integer> colCod;
    @FXML
    private TableColumn<Mascota, String> colNom;
    @FXML
    private TableColumn<Mascota, String> colTipo;
    @FXML
    private TableColumn<Dueño, Dueño> colDueño;
    @FXML
    private TableColumn<Dueño, Void> colOpc;


    @FXML
    private void initialize(){
        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colDueño.setCellValueFactory(new PropertyValueFactory<>("Dueño"));
        tvMascotas.getItems().setAll(Aplicacion.listaMascotas);
    }
    
    @FXML
    private void switchToCrearMascota(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearMascota.fxml"));
            CrearMascotaController ct = new CrearMascotaController();

            fxmlLoader.setController(ct);

            VBox root = (VBox) fxmlLoader.load();
            Aplicacion.changeRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        Aplicacion.setRoot("principalMenu");
    }
    
    
    
}
