/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Auspiciante;
import com.mycompany.modelo.Premio;
import com.mycompany.modelo.Dueño;
import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.Mascota;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;


/**
 * FXML Controller class
 *
 * @author alex_
 */
public class AdministrarCiudadesController {


    @FXML
    private VBox primary;
    @FXML
    private TableView<Ciudad> tvCiudades;
    @FXML
    private Button btAgregarCiudad;
    @FXML
    private Button btRegresar;
    @FXML
    private TableColumn<Ciudad, Integer> colCod;
    @FXML
    private TableColumn<Ciudad, String> colNom;
    @FXML
    private TableColumn<Ciudad, String> colProv;
    @FXML
    private TableColumn<Ciudad, Void> colOpc;
    
    @FXML///cambiar
    private void switchToCrearCiudades(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearCiudad.fxml"));
            CrearCiudadController ct = new CrearCiudadController();

            fxmlLoader.setController(ct);

            VBox root = (VBox) fxmlLoader.load();
            Aplicacion.changeRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {

        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colProv.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        actualizarListaCiudades();


        agregarOpciones();//en este metodo se llenan los botones para cada fila

        //datos en listview
        tvCiudades.getItems().setAll(Aplicacion.listaCiudades);

    }

    
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        Aplicacion.setRoot("principalMenu");
    }

    @FXML
    private void agregarOpciones() {

        Callback<TableColumn<Ciudad, Void>, TableCell<Ciudad, Void>> cellFactory = new Callback<TableColumn<Ciudad, Void>, TableCell<Ciudad, Void>>() {
            @Override
            public TableCell<Ciudad, Void> call(final TableColumn<Ciudad, Void> param) {
                TableCell<Ciudad, Void> cell = new TableCell<Ciudad, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el concurso de la fila
                            Ciudad city = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(e ->editarCiudad(city.getNombre()));
                               
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e -> eliminarCiudad(city.getNombre()));
                            
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


    private void editarCiudad(String nombre_ciudad) {
        Ciudad city = Aplicacion.encontrarCiudad(nombre_ciudad);
        System.out.println("comienza edicion de ciudad");
        System.out.println(city);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearCiudad.fxml"));
            CrearCiudadController ct = new CrearCiudadController();

            fxmlLoader.setController(ct);

            VBox root = (VBox) fxmlLoader.load();
            
            ct.llenarCampos(city);
            ct.edicionCiudad(city);
            actualizarListaCiudades();
            Aplicacion.changeRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarCiudad(String nombre_city) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar una ciudad");
        alert.setHeaderText("Notificacion");
        alert.setContentText("Esta seguro que desea eliminar esta ciudad?");
    
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            Ciudad city = Aplicacion.encontrarCiudad(nombre_city);
            System.out.println(city);
            Aplicacion.listaCiudades.remove(city);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/ciudades.csv"))) {
                bw.write("id,ciudad,provincia");
                bw.newLine();
                for (Ciudad c : Aplicacion.listaCiudades) {
                    System.out.println(c);
                    bw.write(c.getCodigo() + "," + c.getNombre() + "," + c.getProvincia());
                    bw.newLine();
                    
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
            actualizarListaCiudades();
        } else {
            
        }
    }

    @FXML
    public void actualizarListaCiudades() {     
            ArrayList<Ciudad> listado_actualizado = Aplicacion.listaCiudades;
            tvCiudades.getItems().clear();
            agregarOpciones();//en este metodo se llenan los botones para cada fila
            tvCiudades.getItems().setAll(listado_actualizado);
            for (Ciudad c : listado_actualizado) {
                System.out.println(c);
            }
    }
}
