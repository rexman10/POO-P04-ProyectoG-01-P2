package com.mycompany.proyecto_poo_mascotas_fx_p2;

import java.util.ArrayList;

import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.Mascota;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InscripcionMascotaController {

    @FXML
    private Button btInscribir;

    @FXML
    private Button btRegresar;

    @FXML
    private ComboBox<Mascota> cbMascotas;

    @FXML
    private Label lbConcurso;

    public void initialize() {

    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) lbConcurso.getScene().getWindow();
        stage.close();
    }

    public void llenarMascotas(Concurso conc) {
        System.out.println(Aplicacion.listaConcursos);
        System.out.println("===========");
        System.out.println(conc);
        lbConcurso.setText(conc.getNombre());
        String comparacion = conc.getDirigido();
        System.out.println(comparacion);
        ArrayList<Mascota> copia = new ArrayList<>();
        if (comparacion.equals("Perros")) {
            for (Mascota mascota : Aplicacion.listaMascotas) {
                if (mascota.getTipoMascota().equals("Perro")) {
                    copia.add(mascota);
                }
            }
        } else if (comparacion.equals("Gatos")) {
            for (Mascota mascota : Aplicacion.listaMascotas) {
                if (mascota.getTipoMascota().equals("Gato")) {
                    copia.add(mascota);
                }
            }
        } else {
            for (Mascota mascota : Aplicacion.listaMascotas) {
                copia.add(mascota);
            }
        }
        cbMascotas.getItems().setAll(copia);
    }

    @FXML
    public void inscribirse() {
        System.out.println("se apreto");
        System.out.println(AdministrarConcursosController.seleccion_actual);
        AdministrarConcursosController.seleccion_actual.getListaConcursantes().add(cbMascotas.getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText("Mascsota inscrita exitosamente");
        System.out.println(AdministrarConcursosController.seleccion_actual.getListaConcursantes());
        alert.showAndWait();
        cerrarVentana();
    }
}