/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import modelo.Ciudad;
import modelo.Auspiciante;
import modelo.Premio;
import modelo.Dueño;
import modelo.Concurso;
import modelo.Mascota;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;


/**
 * FXML Controller class
 *
 * @author alex_
 */
public class AdministrarConcursosController {


    @FXML
    private VBox primary;
    @FXML
    private TableView<Concurso> tvConcursos;
    @FXML
    private Button crearDueño;
    @FXML
    private Button regresarMenu;
    @FXML
    private TableColumn<Concurso, Integer> colCod;
    @FXML
    private TableColumn<Concurso, String> colNom;
    @FXML
    private TableColumn<Concurso, String> colFecha;
    @FXML
    private TableColumn<Concurso, String> colCiudad;
    @FXML
    private TableColumn<Concurso, Void> colOpc;
    
    @FXML
    private void switchToCrearConcurso(ActionEvent event) throws IOException {
        Aplicacion.setRoot("crearConcurso");
    }

    @FXML
    private void initialize() {

        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("temporal"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));


        agregarOpciones();//en este metodo se llenan los botones para cada fila

        //datos en listview
        tvConcursos.getItems().setAll(Concurso.cargarConcursos("listaConcursos.ser"));

    }

    
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        Aplicacion.setRoot("principalMenu");
    }

    private void agregarOpciones() {

        Callback<TableColumn<Concurso, Void>, TableCell<Concurso, Void>> cellFactory = new Callback<TableColumn<Concurso, Void>, TableCell<Concurso, Void>>() {
            @Override
            public TableCell<Concurso, Void> call(final TableColumn<Concurso, Void> param) {
                TableCell<Concurso, Void> cell = new TableCell<Concurso, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el empleado de la fila
                            Concurso conc = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(e ->editarConcurso(conc));
                               
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            //este boton si inhabilita para genero femenino
                            if (conc.getFecha().before(Calendar.getInstance()))
                                btnEl.setDisable(true);
                            btnEl.setOnAction(e -> eliminarConcurso(conc.getCodigo()));
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnEl);
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                        }
                    }
                };
                return cell;
            }
        };

        colOpc.setCellFactory(cellFactory);

    }

    private void editarConcurso(Concurso c) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("nuevo.fxml"));//no tiene el controlador especificado
            CrearConcursoController ct = new CrearConcursoController();
            
            fxmlLoader.setController(ct);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();
            
            //ct.llenarCombo(Departamento.cargarDepartamentos(App.pathDep));
            //ct.llenarCampos(e);
            //Aplicacion.changeRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void eliminarConcurso(int c) {
        Concurso conc = Aplicacion.encontrarConcurso(c);
        System.out.println(conc);
        Aplicacion.listaConcursos.remove(conc);
        Aplicacion.cargarBaseDatos();
        for (Concurso concurso : Aplicacion.listaConcursos) {
            System.out.println(concurso);
        }
    }




}
