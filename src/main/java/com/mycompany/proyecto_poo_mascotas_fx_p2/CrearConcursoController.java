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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import com.mycompany.modelo.Auspiciante;
import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.Fechas;
import com.mycompany.modelo.Premio;


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
    private DatePicker dpInicio;
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
    private TableColumn<Premio, Integer> colPuesto;
    @FXML
    private TableColumn<Premio, String> colDescripcion;
    @FXML
    private TableColumn<Premio, Auspiciante> colAuspiciante;
    @FXML
    private Button btAgregarPremio;
    @FXML
    private Label lblCabecera;




    @FXML
    private void initialize() {
        llenarNuevoConc();
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colAuspiciante.setCellValueFactory(new PropertyValueFactory<>("auspiciante"));
    }
 
    @FXML
    private void switchToConcurso(ActionEvent event) throws IOException {
        Aplicacion.setRoot("AdministrarConcursos");
    }

    @FXML
    public void llenarNuevoConc () {
        ArrayList<String> a = new ArrayList<>();
        a.add("Perros");
        a.add("Gatos");
        a.add("Todos");
        cbDirigido.getItems().setAll(a);
        cbCiudad.getItems().setAll(Aplicacion.listaCiudades);
        cbAuspiciante.getItems().setAll(Aplicacion.listaAuspiciantes);
    }


    @FXML
    public void llenarCampos(Concurso c) {
        lblCabecera.setText("Editar Concurso");
        cbDirigido.setValue(c.getDirigido());
        txtNombre.setText(c.getNombre());
        dpFecha.setValue(Fechas.calToLocalDate(c.getFecha()));
        tfHora.setText(c.getHora());
        dpInicio.setValue(Fechas.calToLocalDate(c.getFechaInicioInscrip()));
        dpFin.setValue(Fechas.calToLocalDate(c.getFehcaFinInscrip()));
        cbCiudad.setValue(c.getCiudad());
        txtLugar.setText(c.getLugar());
        cbAuspiciante.setValue(c.getAuspiciantesLista());
        ArrayList<Premio> listado = c.getPremios();
        for (Object premio : listado) {
            System.out.println(premio);
        }
        //tvPremios.getItems().setAll(listado);
        


    }
    

    
}
