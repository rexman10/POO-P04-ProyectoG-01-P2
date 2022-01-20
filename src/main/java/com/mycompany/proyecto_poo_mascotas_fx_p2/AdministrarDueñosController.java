/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Dueño;
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
        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApell.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        agregarOpciones();//en este metodo se llenan los botones para cada fila

        //datos en listview
        tvDueños.getItems().setAll(Aplicacion.listaDueños);
    }

    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        Aplicacion.setRoot("principalMenu");
    }

    @FXML
    private void switchToCrearDueño(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearDueño.fxml"));
            CrearDueñoController ct = new CrearDueñoController();

            fxmlLoader.setController(ct);

            VBox root = (VBox) fxmlLoader.load();
            Aplicacion.changeRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                            btnEd.setOnAction(e ->editarDueño(dueño.getCodigo()));
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e -> eliminarDueño(dueño.getCodigo()));
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

    private void editarDueño(int c) {
        Dueño dueño = Dueño.encontrarDueño(c);
        System.out.println("comienza edicion de dueño");
        System.out.println(dueño);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearDueño.fxml"));
            CrearDueñoController ct = new CrearDueñoController();

            fxmlLoader.setController(ct);

            VBox root = (VBox) fxmlLoader.load();
            
            ct.llenarCampos(dueño);
            //ct.edicionDueño(dueño);
            actualizarListaDueños();
            Aplicacion.changeRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarDueño(int c) {
        Dueño d = Dueño.encontrarDueño(c);
        //System.out.println(conc);
        Aplicacion.listaDueños.remove(d);
        actualizarListaDueños();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/duenosP4.csv"))) {
            bw.write("id,apellidos,nombre,direccion,telefono,ciudad,mail");
            bw.newLine();
            for (Dueño dueño : Aplicacion.listaDueños) {
                bw.write(dueño.getCodigo() + "," + dueño.getApellidos() + "," + dueño.getNombre() + "," + dueño.getDireccion() + "," + dueño.getTelefono() + "," + dueño.getCiudad() + "," + dueño.getEmail());
                bw.newLine();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @FXML
    public void actualizarListaDueños() {     
            ArrayList<Dueño> listado_actualizado = Aplicacion.listaDueños;
            tvDueños.getItems().clear();
            agregarOpciones();//en este metodo se llenan los botones para cada fila
            tvDueños.getItems().setAll(listado_actualizado);
    }


}
