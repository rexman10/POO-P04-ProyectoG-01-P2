/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.mycompany.modelo.Auspiciante;
import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.FechasUtil;
import com.mycompany.modelo.FechasUtil;
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
    private Button btActualizarTabla;
    @FXML
    private Label lblCabecera;




    @FXML
    private void initialize() {
        llenarNuevoConc();
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colAuspiciante.setCellValueFactory(new PropertyValueFactory<>("auspiciante"));
        if (CrearPremioController.premios.size() > 0) {
            CrearPremioController.premios.clear();
        }
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
        btGuardarConcurso.setText("Editar");
        btGuardarConcurso.setOnAction(e -> edicionConcurso(c));
        cbDirigido.setValue(c.getDirigido());
        txtNombre.setText(c.getNombre());
        dpFecha.setValue(FechasUtil.calToLocalDate(c.getFecha()));
        tfHora.setText(c.getHora());
        dpInicio.setValue(FechasUtil.calToLocalDate(c.getFechaInicioInscrip()));
        dpFin.setValue(FechasUtil.calToLocalDate(c.getFehcaFinInscrip()));
        cbCiudad.setValue(c.getCiudad());
        txtLugar.setText(c.getLugar());
        cbAuspiciante.setValue(c.getAuspiciante());
        ArrayList<Premio> listado = c.getPremios();
        tvPremios.getItems().setAll(listado);
    }

    @FXML
    public void nuevoPremio() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("crearPremio.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Nuevo Premio");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTabla() {
        ArrayList<Premio> lista_premios = (ArrayList<Premio>) CrearPremioController.premios.clone();
        tvPremios.getItems().setAll(lista_premios);
    }
    
    @FXML
    private void guardarNuevoConcurso() {
        ArrayList<Concurso> concursos = Concurso.cargarConcursos("archivos/listaConcursos.ser");//cargar la lista del archivo
        System.out.println("Guardando concurso");
        String dirig = cbDirigido.getValue();
        String nombre = txtNombre.getText();
        String[] fecha_dp = dpFecha.getValue().toString().split("-");
        Calendar fecha_new = new GregorianCalendar(Integer.parseInt(fecha_dp[0]),Integer.parseInt(fecha_dp[1]),Integer.parseInt(fecha_dp[2]));
        String hora = tfHora.getText();
        String[] fecha_in = dpInicio.getValue().toString().split("-");
        Calendar fecha_new_in = new GregorianCalendar(Integer.parseInt(fecha_in[0]),Integer.parseInt(fecha_in[1]),Integer.parseInt(fecha_in[2]));
        String[] fecha_fin = dpFin.getValue().toString().split("-");
        Calendar fecha_new_fin = new GregorianCalendar(Integer.parseInt(fecha_fin[0]),Integer.parseInt(fecha_fin[1]),Integer.parseInt(fecha_fin[2]));
        Ciudad city = cbCiudad.getValue();
        String lugar = txtLugar.getText();
        Auspiciante ausp_new = cbAuspiciante.getValue();
        ObservableList<Premio> obs_list = tvPremios.getItems();
        ArrayList<Premio> premios = new ArrayList<>(obs_list);
        //for (Premio premio : premios) {
        //    System.out.println(premio);
        //}
        Concurso temp = new Concurso(nombre, fecha_new, hora, fecha_new_in, fecha_new_fin, city, lugar, premios, ausp_new, dirig);
        int id_comprobacion = temp.getCodigo();
        System.out.println("llegando a comprobacion");
        System.out.println(Aplicacion.concursoExiste(id_comprobacion));
        if (!Aplicacion.concursoExiste(id_comprobacion)) {
            Aplicacion.listaConcursos.add(temp);
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/listaConcursos.ser"))) {
                out.writeObject(Aplicacion.listaConcursos);
                //mostrar informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Resultado de la operación");
                alert.setContentText("Nuevo concurso agregado exitosamente");

                alert.showAndWait();
                Aplicacion.setRoot("AdministrarConcursos");
            }
            catch (IOException e) {
                e.getMessage();
            }
        } else {
        }


        //Concurso c = new Concurso();
        //concursos.add(c);//agregar empleado a la lista
        //System.out.println("Nuevo Concurso:" + c);
        
        //serializar la lista
        //try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathEmpleados))){
        //    out.writeObject(empleados);
        //    out.flush();



        //} catch (IOException ex) {
        //    System.out.println("IOException:" + ex.getMessage());
        //} 

    }

    @FXML
    public void edicionConcurso(Concurso c) {
        c.setDirigido(cbDirigido.getValue());
        c.setNombre(txtNombre.getText());
        String[] fecha_dp = dpFecha.getValue().toString().split("-");
        Calendar fecha_new = new GregorianCalendar(Integer.parseInt(fecha_dp[0]),Integer.parseInt(fecha_dp[1]),Integer.parseInt(fecha_dp[2]));
        c.setFecha(fecha_new);
        c.setHora(tfHora.getText());;
        String[] fecha_in = dpInicio.getValue().toString().split("-");
        Calendar fecha_new_in = new GregorianCalendar(Integer.parseInt(fecha_in[0]),Integer.parseInt(fecha_in[1]),Integer.parseInt(fecha_in[2]));
        c.setFechaInicioInscrip(fecha_new_in);
        String[] fecha_fin = dpFin.getValue().toString().split("-");
        Calendar fecha_new_fin = new GregorianCalendar(Integer.parseInt(fecha_fin[0]),Integer.parseInt(fecha_fin[1]),Integer.parseInt(fecha_fin[2]));
        c.setFehcaFinInscrip(fecha_new_fin);
        c.setCiudad(cbCiudad.getValue());
        c.setLugar(txtLugar.getText());
        ObservableList<Premio> obs_list = tvPremios.getItems();
        ArrayList<Premio> premios = new ArrayList<>(obs_list);
        c.setListaPremios(premios);
        if (btGuardarConcurso.isArmed()) {
            System.out.println("entra al if");
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/listaConcursos.ser"))) {
                out.writeObject(Aplicacion.listaConcursos);
                //mostrar informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Resultado de la operación");
                alert.setContentText("Concurso editado exitosamente");
                alert.showAndWait();
                Aplicacion.setRoot("AdministrarConcursos");
            }
            catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
