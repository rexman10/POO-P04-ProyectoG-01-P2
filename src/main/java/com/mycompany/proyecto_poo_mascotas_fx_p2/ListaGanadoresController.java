package com.mycompany.proyecto_poo_mascotas_fx_p2;

import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.Ganador;
import com.mycompany.modelo.Mascota;
import com.mycompany.modelo.Premio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import com.mycompany.modelo.Mascota;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class ListaGanadoresController {

    @FXML
    private Button btRegresar;
    @FXML
    private TableView<Ganador> tvGanadores;
    @FXML
    private TableColumn<Ganador, Integer> colPuesto;
    @FXML
    private TableColumn<Ganador, String> colPremio;
    @FXML
    private TableColumn<Ganador, String> colMascota;

    public void initialize() {
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        colPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));
        colMascota.setCellValueFactory(new PropertyValueFactory<>("mascota"));
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) tvGanadores.getScene().getWindow();
        stage.close();
    }

    public void llenarCampos(Concurso c) {
        ArrayList<Mascota> listado = c.getListaGanadores();
        Mascota m1 = listado.get(0);
        Mascota m2 = listado.get(1);
        Mascota m3 = listado.get(2);
        ArrayList<Premio> lista_premios = c.getPremios();
        Premio p1 = lista_premios.get(0);
        Premio p2 = lista_premios.get(1);
        Premio p3 = lista_premios.get(2);
        //System.out.println(listado);
        //System.out.println(lista_premios);
        ObservableList<Ganador> data = FXCollections.observableArrayList(
            new Ganador(1,m1.getNombre(),p1.getDescripcion()),
            new Ganador(2,m2.getNombre(),p2.getDescripcion()),
            new Ganador(3,m3.getNombre(),p3.getDescripcion())
        );
        tvGanadores.getItems().setAll(data);
        
    }
}   
