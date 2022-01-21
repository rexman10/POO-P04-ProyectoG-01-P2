/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Auspiciante;
import com.mycompany.modelo.Premio;
import com.mycompany.modelo.Dueño;
import com.mycompany.modelo.Fechas;
import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.Correo;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

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
    private Button btEnviarInvitaciones;
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
    
    public static Concurso seleccion_actual = new Concurso();
    
    @FXML
    private void switchToCrearConcurso(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearConcurso.fxml"));
            CrearConcursoController ct = new CrearConcursoController();

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
        colFecha.setCellValueFactory(new PropertyValueFactory<>("temporal"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));


        agregarOpciones();//en este metodo se llenan los botones para cada fila

        //datos en listview
        tvConcursos.getItems().setAll(Concurso.cargarConcursos("archivos/listaConcursos.ser"));

    }

    
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        Aplicacion.setRoot("principalMenu");
    }

    @FXML
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
                            //recuperar el concurso de la fila
                            Concurso conc = getTableView().getItems().get(getIndex());
                            seleccion_actual = conc;
                            //boton editar
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(e ->editarConcurso(conc.getCodigo()));
                            if (conc.getFecha().before(Calendar.getInstance())) {
                                btnEd.setDisable(true);
                            }
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            if (conc.getFecha().before(Calendar.getInstance()))
                                btnEl.setDisable(true);
                            btnEl.setOnAction(e -> eliminarConcurso(conc.getCodigo()));

                            Button btnConsultGanadores = new Button("Ganadores");
                            if (conc.getFecha().after(Calendar.getInstance()))
                                btnConsultGanadores.setDisable(true);
                            btnConsultGanadores.setOnAction(e -> consultarGanadores(conc));

                            Button btnInscribirMascotas = new Button("Inscribir Mascota");
                            if (conc.getFecha().before(Calendar.getInstance())) {
                                btnInscribirMascotas.setDisable(true);
                            }
                            btnInscribirMascotas.setOnAction(e -> inscribirMascota(conc));
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnEl,btnInscribirMascotas,btnConsultGanadores);
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


    private void editarConcurso(int c) {
        Concurso conc = Aplicacion.encontrarConcurso(c);
        System.out.println("comienza edicion de concurso");
        System.out.println(conc);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearConcurso.fxml"));
            CrearConcursoController ct = new CrearConcursoController();

            fxmlLoader.setController(ct);

            VBox root = (VBox) fxmlLoader.load();
            
            ct.llenarCampos(conc);
            ct.edicionConcurso(conc);
            actualizarListaConcursos();
            Aplicacion.changeRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarConcurso(int c) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar un concurso");
        alert.setHeaderText("Notificacion");
        alert.setContentText("Esta seguro que desea eliminar este concurso?");
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Concurso conc = Aplicacion.encontrarConcurso(c);
            System.out.println(conc);
            Aplicacion.listaConcursos.remove(conc);
            for (Concurso concurso : Aplicacion.listaConcursos) {
                System.out.println(concurso);
            }
            actualizarListaConcursos();
        } else {
            
        }

    }

    @FXML
    private void consultarGanadores(Concurso c) {
        c.ganadores();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("ListaGanadores.fxml"));
            ListaGanadoresController ct = new ListaGanadoresController();
            fxmlLoader.setController(ct);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Ganadores");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            ct.llenarCampos(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void inscribirMascota(Concurso conc) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("inscripcionMascotas.fxml"));
            InscripcionMascotaController ct = new InscripcionMascotaController();
            fxmlLoader.setController(ct);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Inscripcion");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            ct.llenarMascotas(conc);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actualizarListaConcursos() {
        try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream("archivos/listaConcursos.ser"))) {
            ou.writeObject(Aplicacion.listaConcursos);
            ou.close();
        }

        catch(IOException ex){
            System.out.println("IOException is caught");
        }
        
        try {
            ArrayList<Concurso> listado_actualizado = (ArrayList<Concurso>) Concurso.cargarConcursos("archivos/listaConcursos.ser");
            tvConcursos.getItems().clear();
            agregarOpciones();//en este metodo se llenan los botones para cada fila
            tvConcursos.getItems().setAll(listado_actualizado);
            for (Concurso concurso : listado_actualizado) {
                System.out.println(concurso);
            }
        }
        finally {
            System.out.println("serializacion completa");
        }
        //datos en listview

    }

    @FXML
    public void enviarInvitaciones() {
        for (Concurso concurso : Aplicacion.listaConcursos) {
            System.out.println(concurso.getNombre());
            if (concurso.getFecha().after(Calendar.getInstance())) {
                System.out.println("Enviado correo...");
                String destinatario = "jaguadal@espol.edu.ec";
                String asunto = "Invitacion concurso de mascotas";
                String cuerpo = "Nuevo concurso en la ciudad de " + concurso.getCiudad() + "\n" + "Te invitamos a inscribirte en: " + concurso.getNombre() + "\n" + "Las inscripciones esta abiertas desde el " + Fechas.convert(concurso.getFechaInicioInscrip()) + " hasta el " + Fechas.convert(concurso.getFehcaFinInscrip()) + "\n" + "Ubicacion: " + concurso.getLugar() + "\n" + "Hora: " + concurso.getHora() + "\n" + "!No te lo pierdas¡";
                Correo.enviarInvitaciones(destinatario, asunto, cuerpo);
                System.out.println("Correo enviado!");
            }
        }


    }

}
