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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

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

                            //Boton Detalle
                            Button btnDet = new Button("Detalle");
                            btnDet.setOnAction(e -> mostrarDetalle(mascota));
                            //boton editar
                            Button btnEd = new Button("Editar");
                            //int a = mascota.getCodigo();
                            btnEd.setOnAction(e -> {
                                System.out.println(mascota);
                                editarMascota(mascota);
                                System.out.println("-- click en editar --");
                            });  //);editarMascota(1));   //(dueño.getCodigo()));
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            //btnEl.setOnAction(e -> eliminarDueño(dueño.getCodigo()));
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnDet, btnEd, btnEl);
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

    private void editarMascota(Mascota m) {
        //Mascota mascotaEdiatada = Mascota.
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearMascota.fxml"));
            CrearMascotaController ct = new CrearMascotaController();

            fxmlLoader.setController(ct);

            VBox root = (VBox) fxmlLoader.load();
            ct.llenarCampos(m);
            Aplicacion.changeRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void mostrarDetalle(Mascota m) {
        DetalleMascotaController.lista_temp.add(m);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("DetalleMascota.fxml"));
            DetalleMascotaController ct = new DetalleMascotaController();
            fxmlLoader.setController(ct);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Detalle Mascota");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("================");
            System.out.println(m);
            System.out.println(m.getNombre());
            System.out.println(m.getFechaNacimiento());
            System.out.println(m.getRaza());
            System.out.println(m.getDuenio().getCredenciales());
            System.out.println(m.getUrlFoto());
            System.out.println("================");
            stage.show();            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actualizarListaMascotas() {
        ArrayList<Mascota> listado_actualizado = Aplicacion.listaMascotas;
        tvMascotas.getItems().clear();
        agregarOpciones();//en este metodo se llenan los botones para cada fila
        tvMascotas.getItems().setAll(listado_actualizado);
    }
}
