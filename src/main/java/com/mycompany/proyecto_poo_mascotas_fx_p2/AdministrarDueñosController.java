/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.IOException;

import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Dueño;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class AdministrarDueñosController {

    @FXML
    private Label LbTitulo;
    @FXML
    private TableView<Dueño> tvDueños;
    @FXML
    private Button btCrearDueño;
    @FXML
    private Button regresarMenu;
    @FXML
    private TableColumn<Dueño, Integer> colCod;
    @FXML
    private TableColumn<Dueño, String> colNom;
    @FXML
    private TableColumn<Dueño, String> colApell;
    @FXML
    private TableColumn<Dueño, String> colTel;
    @FXML
    private TableColumn<Dueño, Ciudad> colCiudad;
    @FXML
    private TableColumn<Dueño, Void> colOpc;

    @FXML
    private void initialize() {
        colCod.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApell.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        agregarOpciones();//en este metodo se llenan los botones para cada fila

        //datos en listview
        tvDueños.getItems().setAll(Dueño.cargarDueños("archivos/duenios.csv"));
    }

    @FXML
    private void agregarOpciones() {

        Callback<TableColumn<Dueño, Void>, TableCell<Dueño, Void>> cellFactory = new Callback<TableColumn<Dueño, Void>, TableCell<Dueño, Void>>() {
            @Override
            public TableCell<Dueño, Void> call(final TableColumn<Dueño, Void> param) {
                TableCell<Dueño, Void> cell = new TableCell<Dueño, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el concurso de la fila
                            Dueño dueño = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            //btnEd.setOnAction(e ->editarConcurso(dueño.getCodigo()));
                               
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            //este boton si inhabilita para genero femenino
                            //if (dueño.getFecha().before(Calendar.getInstance()))
                            //    btnEl.setDisable(true);
                            //btnEl.setOnAction(e -> eliminarConcurso(conc.getCodigo()));

                            Button btnConsultGanadores = new Button("Ganadores");
                            //if (conc.getFecha().after(Calendar.getInstance()))
                            //    btnConsultGanadores.setDisable(true);
                            //btnConsultGanadores.setOnAction(e -> consultarGanadores(conc));
                                

                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnEl,btnConsultGanadores);
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

    @FXML
    private void switchToCrearDueño(ActionEvent event) throws IOException {
        Aplicacion.setRoot("crearDueño");
    }
    
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        Aplicacion.setRoot("principalMenu");
    }


}
