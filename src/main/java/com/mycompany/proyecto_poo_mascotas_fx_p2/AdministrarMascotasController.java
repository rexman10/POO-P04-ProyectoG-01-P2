/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Dueño;
import com.mycompany.modelo.Mascota;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
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
    private Label lbTitulo;
    @FXML
    private TableView<Mascota> tvMascotas;
    @FXML
    private Button btAgregarMascota;
    @FXML
    private Button btCancelarMascota;
    @FXML
    private TableColumn<Mascota, Integer> colCod;
    @FXML
    private TableColumn<Mascota, String> colNom;
    @FXML
    private TableColumn<Mascota, String> colTipo;
    @FXML
    private TableColumn<Mascota, Dueño> colDueño;
    @FXML
    private TableColumn<Mascota, Void> colOpc;

    @FXML
    private void initialize() {
        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoMascota"));
        colDueño.setCellValueFactory(new PropertyValueFactory<>("duenio"));
        agregarOpciones();//en este metodo se llenan los botones para cada fila
        actualizarListaMascotas();

        //datos en listview
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

    @FXML
    private void agregarOpciones() {

        Callback<TableColumn<Mascota, Void>, TableCell<Mascota, Void>> cellFactory = new Callback<TableColumn<Mascota, Void>, TableCell<Mascota, Void>>() {
            @Override
            public TableCell<Mascota, Void> call(final TableColumn<Mascota, Void> param) {
                TableCell<Mascota, Void> cell = new TableCell<Mascota, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el concurso de la fila
                            Mascota mascota = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            //btnEd.setOnAction(e ->editarDueño(dueño.getCodigo()));
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            //btnEl.setOnAction(e -> eliminarDueño(dueño.getCodigo()));
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

    @FXML
    public void actualizarListaMascotas() {     
            ArrayList<Mascota> listado_actualizado = Aplicacion.listaMascotas;
            tvMascotas.getItems().clear();
            agregarOpciones();//en este metodo se llenan los botones para cada fila
            tvMascotas.getItems().setAll(listado_actualizado);
    }
}
