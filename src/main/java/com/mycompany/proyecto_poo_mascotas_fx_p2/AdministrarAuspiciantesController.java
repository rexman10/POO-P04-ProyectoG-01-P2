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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Auspiciante;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class AdministrarAuspiciantesController {
    
    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<Auspiciante> tvAuspiciante;
    @FXML
    private Button btAgregarAuspiciante;
    @FXML
    private Button btCancelarAuspiciante;
    @FXML
    private TableColumn<Auspiciante, Integer> colCod;
    @FXML
    private TableColumn<Auspiciante, String> colNom;
    @FXML
    private TableColumn<Auspiciante, String> colTelefono;
    @FXML
    private TableColumn<Auspiciante, Ciudad> colCiudad;
    @FXML
    private TableColumn<Auspiciante, Void> colOpc;

    @FXML
    private void initialize() {
        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        agregarOpciones();//en este metodo se llenan los botones para cada fila
        actualizarListaAuspiciante();

        //datos en listview
        tvAuspiciante.getItems().setAll(Aplicacion.listaAuspiciantes);
    }
    

    @FXML
    private void switchToCrearAuspiciante(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearAuspiciante.fxml"));
            CrearAuspicianteController ct = new CrearAuspicianteController();

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

        Callback<TableColumn<Auspiciante, Void>, TableCell<Auspiciante, Void>> cellFactory = new Callback<TableColumn<Auspiciante, Void>, TableCell<Auspiciante, Void>>() {
            @Override
            public TableCell<Auspiciante, Void> call(final TableColumn<Auspiciante, Void> param) {
                TableCell<Auspiciante, Void> cell = new TableCell<Auspiciante, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el concurso de la fila
                            Auspiciante auspiciante = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(e ->editarAuspiciante(auspiciante.getCodigo()));
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e -> eliminarAuspiciante(auspiciante.getCodigo()));
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
    private void editarAuspiciante(int cod_auspiciante) {
        Auspiciante ausp = Aplicacion.encontrarAuspiciante(cod_auspiciante);
        System.out.println("comienza edicion de auspiciante");
        System.out.println(ausp);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearAuspiciante.fxml"));
            CrearAuspicianteController ct = new CrearAuspicianteController();

            fxmlLoader.setController(ct);

            VBox root = (VBox) fxmlLoader.load();
            
            ct.llenarCampos(ausp);
            ct.edicionAuspiciante(ausp);
            actualizarListaAuspiciante();
            Aplicacion.changeRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarAuspiciante(int cod_auspiciante) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar un auspiciante");
        alert.setHeaderText("Notificacion");
        alert.setContentText("Esta seguro que desea eliminar este auspiciante?");
    
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            Auspiciante ausp = Aplicacion.encontrarAuspiciante(cod_auspiciante);
            System.out.println(ausp);
            System.out.println("XDXDXD");
            Aplicacion.listaAuspiciantes.remove(ausp);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/auspiciantes.csv"))) {
                bw.write("id,nombre,direccion,telefono,ciudad,email,webpage");
                bw.newLine();
                for (Auspiciante a : Aplicacion.listaAuspiciantes) {
                    System.out.println(a);
                    bw.write(a.getCodigo() + "," + a.getNombre() + "," + a.getDireccion() + "," + a.getTelefono() + "," + a.getCiudad() + "," + a.getEmail() + "," + a.getWebPage());
                    bw.newLine();
                    
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
            actualizarListaAuspiciante();
        } else {
            
        }
    }

    @FXML
    public void actualizarListaAuspiciante() {     
            ArrayList<Auspiciante> listado_actualizado = Aplicacion.listaAuspiciantes;
            tvAuspiciante.getItems().clear();
            agregarOpciones();//en este metodo se llenan los botones para cada fila
            tvAuspiciante.getItems().setAll(listado_actualizado);
    }
}
