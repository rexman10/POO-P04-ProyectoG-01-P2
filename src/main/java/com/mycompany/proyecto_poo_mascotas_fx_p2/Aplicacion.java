package com.mycompany.proyecto_poo_mascotas_fx_p2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex_
 */

import com.mycompany.modelo.Ciudad;
import com.mycompany.modelo.Auspiciante;
import com.mycompany.modelo.Premio;
import com.mycompany.modelo.Dueño;
import com.mycompany.modelo.FechasUtil;
import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.Direccion;
import com.mycompany.modelo.Mascota;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.application.Application;

public class Aplicacion extends Application implements DataBase { 
    public static ArrayList<Dueño> listaDueños;
    public static ArrayList<Mascota> listaMascotas;
    public static ArrayList<Auspiciante> listaAuspiciantes;
    public static ArrayList<Ciudad> listaCiudades;
    public static ArrayList<Concurso> listaConcursos;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principalMenu"), 640, 680);
        stage.setScene(scene);
        scene.getStylesheets().add(Aplicacion.class.getResource("css/estilos.css").toExternalForm());
        stage.show();

    }
    

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }

    public static void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static boolean dueñoExiste(int id){
        //Recibe como parámetro la cedula de un dueño y devuelve true si este se encuentra en la base de datos, caso contrario devuelve false//
        Dueño busqueda = new Dueño(id);
        return listaDueños.contains(busqueda);
    }


    public static boolean auspicianteExiste(int codigo){
        //Recibe como parámetro el código de una mascota y devuelve true si este se encuentra en la base de datos, caso contrario devuelve false//
        Auspiciante busqueda = new Auspiciante(codigo);
        return listaAuspiciantes.contains(busqueda);
    }

    public static boolean ciudadExiste(int cod){
        //Recibe como parámetro el código de una ciudad y devuelve true si este se encuentra en la base de datos, caso contrario devuelve false//
        Ciudad busqueda = new Ciudad(cod);
        return listaCiudades.contains(busqueda);
    }

    public static Auspiciante encontrarAuspiciante(int codigo) {
        //Recibe como parámetro el código de una mascota y lo busca en la base de datos, si lo encuentra retorna la mascota en cuestion, caso contrario retorna null//
        for(Auspiciante ausp : listaAuspiciantes) {
            if(ausp.getCodigo()== codigo) {
                return ausp;
            }
        }
        return null;
    }

    public static boolean concursoExiste(int codigo){
        //Recibe como parámetro el código de un concurso y devuelve true si este se encuentra en la base de datos, caso contrario devuelve false//
        Concurso busqueda = new Concurso(codigo);
        return listaConcursos.contains(busqueda);
    }

    public static Concurso encontrarConcurso(int codigo) {
        //Recibe como parámetro el código de un concurso y lo busca en la base de datos, si lo encuentra retorna el concurso en cuestion caso contrario retorna null//
        for(Concurso conc : listaConcursos) {
            if(conc.getCodigo() == codigo) {
                return conc;
            }
        }
        return null;
    }

    public static boolean mascotaExiste(int codigo){
        //Recibe como parámetro el código de una mascota y devuelve true si este se encuentra en la base de datos, caso contrario devuelve false//
        Mascota busqueda = new Mascota(codigo);
        return listaMascotas.contains(busqueda);
    }
    
    public Mascota encontrarMascota(int codigo) {
        //Recibe como parámetro el código de una mascota y lo busca en la base de datos, si lo encuentra retorna la mascota en cuestion caso contrario retorna null//
        for(Mascota pet : listaMascotas) {
            if(pet.getCodigo() == codigo) {
                return pet;
            }
        }
        return null;
    }

    public static Ciudad encontrarCiudad(String nombre) {
        //Recibe como parámetro el nombre de una ciudad y lo busca en la base de datos, si lo encuentra retorna la ciudad en cuestion caso contrario retorna null//
        for(Ciudad city : listaCiudades) {
            if(city.getNombre().equals(nombre)) {
                return city;
            }
        }
        return null;
    }

    public static void cargarBaseDatos(){
        ArrayList<Ciudad> lCities = new ArrayList<>();
        listaCiudades = Ciudad.cargarCiudades("archivos/ciudades.csv");
        ArrayList<Mascota> lM = new ArrayList<>();
        listaDueños = Dueño.cargarDueños("archivos/duenosP4.csv");
        lM = Mascota.cargarMascotas("archivos/mascotas.csv");
        listaMascotas = lM;

        Auspiciante auspiciante1 = new Auspiciante("DogChow", "calle1", "0959501881",listaCiudades.get(1), "dogchow@gmail.com", "www.dogchow.com","no");

        ArrayList<Auspiciante> lA = new ArrayList<>();
        lA.add(auspiciante1);


        listaAuspiciantes = Auspiciante.cargarAuspiciantes("archivos/auspiciantes.csv");

        Premio premio_c1_1 = new Premio(1,"200 dolares");
        premio_c1_1.setAuspiciante(auspiciante1);
        Premio premio_c1_2 = new Premio(2,"100 dolares");
        premio_c1_2.setAuspiciante(auspiciante1);
        Premio premio_c1_3 = new Premio(3,"50 dolares");
        premio_c1_3.setAuspiciante(auspiciante1);
        ArrayList<Premio> l_nueva1 = new ArrayList<>();
        l_nueva1.add(premio_c1_1);
        l_nueva1.add(premio_c1_2);
        l_nueva1.add(premio_c1_3);

        Calendar fechaEvento = new GregorianCalendar(2021, Calendar.NOVEMBER, 7);
        Calendar inicioInscrip = new GregorianCalendar(2021, Calendar.OCTOBER, 25);
        Calendar finInscrip = new GregorianCalendar(2021, Calendar.NOVEMBER, 2);
        Concurso c1  = new Concurso("Top Mascotas",fechaEvento,"16:00",inicioInscrip,finInscrip,listaCiudades.get(1),"Estadio local",l_nueva1,auspiciante1,"Todos");

        Calendar fc2 = new GregorianCalendar(2022, Calendar.JANUARY, 30);
        Calendar fin2 = new GregorianCalendar(2022, Calendar.JANUARY, 5);
        Calendar ffin2 = new GregorianCalendar(2022, Calendar.JANUARY, 25);
        Concurso c2 = new Concurso("Firulais", fc2, "20:00", fin2, ffin2, listaCiudades.get(2), "Casa comunal", l_nueva1, auspiciante1,"Perros");

        ArrayList<Concurso> lConc = new ArrayList<>();
        lConc.add(c1);
        lConc.add(c2);

        listaConcursos = lConc;
        
        for (Concurso conc : listaConcursos) {
            if (Calendar.getInstance().after(conc.getFecha())) {
                //System.out.println(conc.getNombre());
                //System.out.println(listaMascotas);
                conc.inscribirMascota(listaMascotas.get(1));
                conc.inscribirMascota(listaMascotas.get(4));
                conc.inscribirMascota(listaMascotas.get(6));
                conc.inscribirMascota(listaMascotas.get(8));
                conc.inscribirMascota(listaMascotas.get(12));

            }
        }

        try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream("archivos/listaConcursos.ser"))) {
            ou.writeObject(listaConcursos);
        } catch (IOException e) {
            e.getMessage();
        }

        listaConcursos = (ArrayList<Concurso>) Concurso.cargarConcursos("archivos/listaConcursos.ser");
        
    }

    public static void main(String[] args){

        Aplicacion.cargarBaseDatos();
        System.out.println("datos cargados");
        //menuPrincipal();
        launch(args);
    }
}
