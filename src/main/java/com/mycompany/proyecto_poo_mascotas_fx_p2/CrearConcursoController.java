/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import modelo.Auspiciante;
import modelo.Ciudad;
import modelo.Concurso;
import modelo.Premio;


/**
 * FXML Controller class
 *
 * @author alex_
 */
public class CrearConcursoController {

    @FXML
    private VBox vbRaiz;
    @FXML
    private Button btCancelarConcurso;
    @FXML
    private Button btGuardarConcurso;
    @FXML
    private ComboBox<String> cbDirigido;
    @FXML 
    private TextField txtNombre;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private TextField tfHora;
    @FXML
    private DatePicker dpinicio;
    @FXML
    private DatePicker dpFin;
    @FXML
    private ComboBox<Ciudad> cbCiudad;
    @FXML 
    private TextField txtLugar;
    @FXML
    private ChoiceBox<Auspiciante> cbAuspiciante;
    @FXML
    private TableView<Premio> tvPremios;
    @FXML
    private TableColumn<Premio, Integer> colCod;
    @FXML
    private TableColumn<Premio, String> colDesc;
    @FXML
    private TableColumn<Premio, String> colAuspiciante;
    @FXML
    private Button btAgregarPremio;
    @FXML
    private Label lblCabecera;




    @FXML
    private void initialize() {
        llenarNuevoConc();
    }
 
    @FXML
    private void switchToConcurso(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarConcursos");
    }

    @FXML
    private void llenarNuevoConc () {
        ArrayList<String> a = new ArrayList<>();
        a.add("Perros");
        a.add("Gatos");
        a.add("Todos");
        cbDirigido.getItems().setAll(a);
        cbCiudad.getItems().setAll(Aplicacion.listaCiudades);
        cbAuspiciante.getItems().setAll(Aplicacion.listaAuspiciantes);
    }


    @FXML
    private void llenarCampos(Concurso c) {
        lblCabecera.setText("Editar Concurso");
        cbDirigido.setValue(c.getDirigido());
        txtNombre.setText(c.getNombre());
        dpFecha.setValue(LocalDate.parse(c.getTemporal()));
        tfHora.setText(c.getHora());
        dpFin.setValue(LocalDate.parse(c.getFechaInicioInscrip().toString()));
        dpFin.setValue(LocalDate.parse(c.getFehcaFinInscrip().toString()));
        cbCiudad.setValue(c.getCiudad());
        txtLugar.setText(c.getLugar());
        cbAuspiciante.setValue(c.getAuspiciantesLista());
        ArrayList<Premio> listado = c.getPremios();
        tvPremios.getItems().setAll(listado);
        


    }
    

    
}
