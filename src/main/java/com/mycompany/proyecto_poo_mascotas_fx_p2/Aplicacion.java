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

import modelo.Ciudad;
import modelo.Auspiciante;
import modelo.Premio;
import modelo.Dueño;
import modelo.Fechas;
import modelo.Concurso;
import modelo.Mascota;
 import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class Aplicacion extends Application { 
    public static ArrayList<Dueño> listaDueños;
    public static ArrayList<Mascota> listaMascotas;
    public static ArrayList<Auspiciante> listaAspiciantes;
    public static ArrayList<Ciudad> listaCiudades;
    public static ArrayList<Concurso> listaConcursos;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principalMenu"), 640, 680);
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }

    public static boolean dueñoExiste(String id){
        //Recibe como parámetro la cedula de un dueño y devuelve true si este se encuentra en la base de datos, caso contrario devuelve false//
        Dueño busqueda = new Dueño(id);
        return listaDueños.contains(busqueda);
    }

    public static Dueño encontrarDueño(String cedula) {
        //Recibe como parámetro la cédula de un dueño y lo busca en la base de datos, si lo encuentra retorna el dueño en cuestion caso contrario retorna null//
        for(Dueño dueño : listaDueños) {
            if(dueño.getCedula().equals(cedula)) {
                return dueño;
            }
        }
        return null;
    }

    public static boolean auspicianteExiste(int codigo){
        //Recibe como parámetro el código de una mascota y devuelve true si este se encuentra en la base de datos, caso contrario devuelve false//
        Auspiciante busqueda = new Auspiciante(codigo);
        return listaAspiciantes.contains(busqueda);
    }

    public static Auspiciante encontrarAuspiciante(int codigo) {
        //Recibe como parámetro el código de una mascota y lo busca en la base de datos, si lo encuentra retorna la mascota en cuestion, caso contrario retorna null//
        for(Auspiciante ausp : listaAspiciantes) {
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

    public static Mascota encontrarMascota(int codigo) {
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
        //Se encarga de generar todos los datos preexistentes para que la aplicación funcione correctamente. No devuelve nada.//

        Ciudad Quito = new Ciudad("Quito", "Pichincha");
        Ciudad Guayaquil = new Ciudad("Guayaquil", "Guayas");
        Ciudad Cuenca = new Ciudad("Cuenca","Azuay");

        ArrayList<Ciudad> lCities = new ArrayList<>();
        lCities.add(Quito);
        lCities.add(Guayaquil);
        lCities.add(Cuenca);

        listaCiudades = lCities;

        Dueño d1 = new Dueño("0952645646", "Juan Alejandro", "Guadalupe Rosas", "Urb. La Romareda", "0959452918", Quito, "jaguadal@espol.edu.ec");
        Dueño d2 = new Dueño("0929548980", "Christofer Paul", "Espin Huayamabe", "Pradera 2", "0995725182", Guayaquil, "cpespin@espol.edu.ec");
        Dueño d3 = new Dueño("0924452625", "Victor Enrique", "Suarez Suarez", "El Batán", " 0985677532", Cuenca, "vistop_schultz69@gmail.com");
        Dueño d4 = new Dueño("0935827528", "David Emiliano", "Rossello Higueras", "Cañaribamba", "0934216226", Cuenca, "ejemplo4@gmail.com");
        Dueño d5 = new Dueño("0927482472", "Miguel Paul", "Batlle Rozas", "Guasmo Norte", "0940106033", Guayaquil, "ejemplo5@gmail.com");
        Dueño d6 = new Dueño("0916482748", "Jose Manuel", "Escribano Julia", "Cotocollao", "0953006438", Quito, "ejemplo6@gmail.com");
        Dueño d7 = new Dueño("0918392840", "Jose Daniel", "Robledo Aguirre", "Totoracocha", "0913441009", Cuenca, "ejemplo7@gmail.com");
        Dueño d8 = new Dueño("0914272194", "Antonio Jesus", "Quevedo Pera", "Barrio Cuba", "0953006438", Guayaquil, "ejemplo8@gmail.com");
        Dueño d9 = new Dueño("0982837282", "Abraham Orlando", "Hernandez Calzada", "Yanuncay", "0960704725", Cuenca, "ejemplo9@gmail.com");
        Dueño d10 = new Dueño("0928493859", "Jose Juan", "Sebastian Vazquez", "Urdesa", "0976082153", Guayaquil, "ejemplo10@gmail.com");

        Mascota m1 = new Mascota("Fifi", "Gato", "persa", "25-12-2017", "", d1);
        Mascota m2 = new Mascota("Coco", "Perro", "labrador", "15-02-2016", "", d2);
        Mascota m3 = new Mascota("Max", "Perro", "golden retriever", "20-05-2016", "", d3);
        Mascota m4 = new Mascota("Rocky", "Gato", "siames", "30-12-2019", "", d4);
        Mascota m5 = new Mascota("Toby", "Perro", "chihuahua", "20-10-2018", "", d5);
        Mascota m6 = new Mascota("Simba", "Gato", "ragdoll", "15-11-2019", "", d6);
        Mascota m7 = new Mascota("Leo", "Gato", "bengala", "10-04-2020", "", d7);
        Mascota m8 = new Mascota("Lucas", "Perro", "caniche", "05-06-2012", "", d8);
        Mascota m9 = new Mascota("Zeus", "Perro", "poodle", "01-07-2014", "", d9);
        Mascota m10 = new Mascota("Bruno", "Gato", "munchkin", "01-09-2018", "", d10);
        
        ArrayList<Mascota> lM = new ArrayList<>();
        lM.add(m1);
        lM.add(m2);
        lM.add(m3);
        lM.add(m4);
        lM.add(m5);
        lM.add(m6);
        lM.add(m7);
        lM.add(m8);
        lM.add(m9);
        lM.add(m10);

        listaMascotas = lM;

        ArrayList<Dueño> lD = new ArrayList<>();
        lD.add(d1);
        lD.add(d2);
        lD.add(d3);
        lD.add(d4);
        lD.add(d5);
        lD.add(d6);
        lD.add(d7);
        lD.add(d8);
        lD.add(d9);
        lD.add(d10);

        listaDueños = lD;

        Auspiciante auspiciante1 = new Auspiciante("DogChow", "calle1", "0959501881",Quito, "dogchow@gmail.com", "www.dogchow.com");
        Auspiciante auspiciante2 = new Auspiciante("Royal Canine", "calle2", "0959452918", Cuenca, "rcanine@gmail.com", "www.royalcanine.com");
        Auspiciante auspiciante3 = new Auspiciante("Purina", "calle3", "0991407561", Guayaquil, "purina@gmail.com", "www.purina.com");
        ArrayList<Auspiciante> lA = new ArrayList<>();
        lA.add(auspiciante1);
        lA.add(auspiciante2);
        lA.add(auspiciante3);

        listaAspiciantes = lA;

        Premio premio_c1 = new Premio("200 dolares", "100 dolares", "50 dolares", auspiciante1);

        Calendar fechaEvento = new GregorianCalendar(2021, Calendar.NOVEMBER, 7);
        Calendar inicioInscrip = new GregorianCalendar(2021, Calendar.OCTOBER, 25);
        Calendar finInscrip = new GregorianCalendar(2021, Calendar.NOVEMBER, 2);
        Concurso c1  = new Concurso("Top Mascotas",fechaEvento,16,inicioInscrip,finInscrip,Quito,"Estadio local",premio_c1,auspiciante1,"Todos");

        Calendar fc2 = new GregorianCalendar(2022, Calendar.JANUARY, 30);
        Calendar fin2 = new GregorianCalendar(2022, Calendar.JANUARY, 5);
        Calendar ffin2 = new GregorianCalendar(2022, Calendar.JANUARY, 25);
        Concurso c2 = new Concurso("Firulais", fc2, 20, fin2, ffin2, Cuenca, "Casa comunal", premio_c1, auspiciante1, "Perros");

        ArrayList<Concurso> lConc = new ArrayList<>();
        lConc.add(c1);
        lConc.add(c2);

        listaConcursos = lConc;

        for (Concurso conc : listaConcursos) {
            if (Calendar.getInstance().after(conc.getFecha())) {
                conc.inscribirMascota(m1);
                conc.inscribirMascota(m3);
                conc.inscribirMascota(m5);
                conc.inscribirMascota(m7);
                conc.inscribirMascota(m9);

            }
        }
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("listaConcursos.ser"))) {
            out.writeObject(listaConcursos);
        }
        catch (IOException e) {
            e.getMessage();
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("duenios.csv"))) {
            //"codigo,nombres,apellidos,telefono,ciudad"
            for (Dueño d : listaDueños) {
                bw.append(d.getCedula() + "," + d.getNombre() + "," + d.getApellidos() + "," + d.getTelefono() + "," + d.getCiudad());
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.getMessage();
        }
        System.out.println("duenios cargados");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("mascotas.csv"))) {
            //"codigo,nombre,tipo,duenio"
            for (Mascota m : listaMascotas) {
                bw.append(m.getCodigo() + "," + m.getNombre() + "," + m.getTipoMascota() + "," + m.getDueño().getCredenciales());
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.getMessage();
        }
        System.out.println("mascotas cargados");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ciudades.csv"))) {
            //"codigo,nombre,provincia"
            for (Ciudad c : listaCiudades) {
                bw.append(c.getCodigo() + "," + c.getNombre() + "," + c.getProvincia());
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.getMessage();
        }
        System.out.println("ciudades cargados");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("auspiciantes.csv"))) {
            //"codigo,nombre,telefono,ciudad"
            for (Auspiciante a : listaAspiciantes) {
                bw.append(a.getCodigo() + "," + a.getNombre() + "," + a.getTelefono() + "," + a.getCiudad());
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.getMessage();
        }
        System.out.println("ausp cargados");
    }

    static Scanner todo = new Scanner(System.in);

    public static Premio crearPremio(){
        //Método que pide por entrada del usuario ingresar los premios de un Concurso. Retorna un objeto de tipo Premio//
        //Scanner premios = new Scanner(System.in);
        System.out.println("Ingrese el primer premio:");
        String p1 = todo.nextLine();
        System.out.println("Ingrese el segundo premio:");
        String p2 = todo.nextLine();
        System.out.println("Ingrese el tercer premio:");
        String p3 = todo.nextLine();
        Premio p = new Premio(p1, p2, p3);
        //premios.close();
        return p;
    }

    public static void crearDueño(){
        //Método que solicita la informacion de un nuevo dueño por teclado y lo añade a la base de datos existente. No retorna nada//
        System.out.println("Creacion de un nuevo dueño:");
        System.out.println("Ingrese la cedula:");
        String cid = todo.nextLine();
        System.out.println("Ingrese los nombres:");
        String nombres = todo.nextLine();
        System.out.println("Ingrese los apellidos:");
        String apellidos = todo.nextLine();
        System.out.println("Ingrese la direccion:");
        String direcc = todo.nextLine();
        System.out.println("Ingrese el telefono:");
        String tel = todo.nextLine();
        System.out.println("Ingrese la ciudad: (Quito,Cuenca,Guayaquil)");
        String city = todo.nextLine();
        Ciudad c = encontrarCiudad(city);
        System.out.println("Ingrese su email:");
        String mail = todo.nextLine();
        listaDueños.add(new Dueño(cid, nombres, apellidos, direcc, tel, c, mail));
        administrarDueños();
    }

    public static void editarDueño() {
        //Permite al usuario modificar algunos de los datos de un dueño asociado a un numero de cédula. No retorna nada solo modifica los valores internamente//
        System.out.println("Ingrese la cedula del dueño a editar:");
        String cedula = todo.nextLine();
        if (dueñoExiste(cedula)) {
            Dueño objetivo = encontrarDueño(cedula);
            System.out.println("Desea editar la direccion? (S/N)");
            String elec1 = todo.nextLine().toUpperCase();
            if (elec1.equals("S")) {
                System.out.println("Escriba la nueva direccion:");
                String new_direccion = todo.nextLine();
                objetivo.setDireccion(new_direccion);
            } 
            System.out.println("Desea editar el telefono? (S/N)");
            String elec2 = todo.nextLine().toUpperCase();
            if (elec2.equals("S")) {
                System.out.println("Escriba el nuevo telefono:");
                String new_telefono = todo.nextLine();
                objetivo.setTelefono(new_telefono);
            } 
            System.out.println("Desea editar el email? (S/N)");
            String elec3 = todo.nextLine().toUpperCase();
            if (elec3.equals("S")) {
                System.out.println("Escriba el nuevo email:");
                String new_mail = todo.nextLine();
                objetivo.setEmail(new_mail);
            } 
        System.out.println("Se han guardado los cambios");
        administrarDueños();
        } else {
            System.out.println("El dueño especificado no existe.");
            administrarDueños();
        }
    }

    public static void inscribirParticipante(){
        //Método que verifica si los concursos se encuantran disponibles para inscripción en base a la fecha
        //actual, los muestra por pantalla y pide al usuario eliga uno en base al codigo de concurso. 
        //Luego solicita la mascota a ser inscrita de acuerdo al concurso
        Calendar hoy = Calendar.getInstance();
        System.out.println();
        System.out.println("Concursos disponibles para inscripcion:");
        for (Concurso conc : listaConcursos) {
            if (conc.getFehcaFinInscrip().after(hoy)) {
                System.out.println(conc);
            }
        }
        System.out.println();
        System.out.println("Ingrese el codigo del concurso al que desea inscribirse:");
        String cod = todo.nextLine();
        Concurso objetivo = encontrarConcurso(Integer.valueOf(cod));
        System.out.println();
        System.out.println("Se inscribira al concurso " + objetivo);
        String comparison = objetivo.getDirigido();
        System.out.println();
        System.out.println("------------------Mascotas Disponibles------------------");
        if (comparison == "Todos") {
            for (Mascota pet : listaMascotas) {
                if (!objetivo.getListaConcursantes().contains(pet)) {
                    System.out.println(pet);
                }
                }
        }
        if (comparison == "Perros") {
            for (Mascota pet : listaMascotas) {
                if (pet.getTipoMascota().equals("Perro") && !objetivo.getListaConcursantes().contains(pet)) {
                    System.out.println(pet);
                }
            }         
        }
        if (comparison == "Gatos") {
            for (Mascota pet : listaMascotas) {
                if (pet.getTipoMascota().equals("Gato") && !objetivo.getListaConcursantes().contains(pet)) {
                    System.out.println(pet);
                }
            }
        }

        System.out.println();
        System.out.println("Escriba el codigo de su mascota:");
        String mascota_code = todo.nextLine();
        Mascota pet = encontrarMascota(Integer.valueOf(mascota_code));
        objetivo.inscribirMascota(pet);
        System.out.println("Se ha inscrito a " + pet.getNombre() + " en el concurso " + objetivo.getNombre());
        System.out.println();
        administrarConcurso();
    }


    public static void crearConcurso(){
        //Método que pide al usuario ingresar por teclado toda la informacion necesaria para crear un nuevo concurso y lo añade a la base de datos//
        System.out.println("Ingrese el nombre del concurso:");
        String n = todo.nextLine();
        System.out.println("Ingrese la fecha del concurso(dd/mm/aaaa):");
        String linea = todo.nextLine();
        String[] datos = linea.split("/", 3);
        int dia = Integer.valueOf(datos[0]);
        int mes = Integer.valueOf(datos[1]);
        int anio = Integer.valueOf(datos[2]);
        Calendar f_evento = new GregorianCalendar(anio, mes, dia);
        System.out.println("Ingrese la hora del concurso:");
        String temp = todo.nextLine();
        int hora = Integer.valueOf(temp);
        System.out.println("Ingrese la fecha de inicio de inscripciones(dd/mm/aaaa):");
        String linea2 = todo.nextLine();
        String[] datos2 = linea2.split("/", 3);
        int dia2 = Integer.valueOf(datos2[0]);
        int mes2 = Integer.valueOf(datos2[1]);
        int anio2 = Integer.valueOf(datos2[2]);
        Calendar inicioInsc = new GregorianCalendar(anio2, mes2, dia2);
        System.out.println("Ingrese la fecha de fin de inscripciones(dd/mm/aaaa):");
        String linea3 = todo.nextLine();
        String[] datos3 = linea3.split("/", 3);
        int dia3 = Integer.valueOf(datos3[0]);
        int mes3 = Integer.valueOf(datos3[1]);
        int anio3 = Integer.valueOf(datos3[2]);
        Calendar finInsc = new GregorianCalendar(anio3, mes3, dia3);
        System.out.println("Las ciudades disponibles son: ");
        for (Ciudad c : listaCiudades) {
            System.out.println(c);
        }
        System.out.println("Ingrese la ciudad donde se realizara el concurso:");
        String city = todo.nextLine();
        Ciudad city_conc = encontrarCiudad(city);
        System.out.println("Ingrese el lugar del evento:");
        String local = todo.nextLine();
        System.out.println("Los auspiciantes disponibles son:");
        for (Auspiciante ausp : listaAspiciantes) {
            System.out.println(ausp);
        }
        System.out.println("Ingrese el codigo del asupiciante que patrocinara el concurso:");
        String cod_ausp = todo.nextLine();
        Auspiciante ausp_conc = encontrarAuspiciante(Integer.valueOf(cod_ausp));
        System.out.println("A quien estara dirigido el concurso?\nPerros (1)\nGatos (2)\nTodos (3)\nDirigido a: ");
        int entry = todo.nextInt();
        String dirig = "";
        if (entry == 1) {
            dirig = "Perros";
        }
        else if (entry == 2) {
            dirig = "Gatos";
        }
        else if (entry == 3) {
            dirig = "Todos";
        }
        System.out.println();
        System.out.println("Especifique los preimos del concurso:");
        todo.nextLine();
        Premio p = crearPremio();
        p.setAuspiciante(ausp_conc);
        listaConcursos.add(new Concurso(n, f_evento, hora, inicioInsc, finInsc, city_conc, local, p, ausp_conc, dirig));
        System.out.println("Se ha creado el concurso:");
        int ultimo = listaCiudades.size();
        System.out.println(listaConcursos.get(ultimo - 1));
        menuPrincipal();
    }

    public static void ganadoresPasados() {
        //Verifica la fecha actual y muestra los concursos pasados para poder elegir uno de ellos en base a su código. Muestra por pantalla los ganadores de dicho concurso//
        Calendar hoy = Calendar.getInstance();
        System.out.println();
        System.out.println("Concursos pasados:");
        for (Concurso conc : listaConcursos) {
            if (conc.getFecha().before(hoy)) {
                System.out.println(conc);
            }
        }
        System.out.println();
        System.out.println("Ingrese el codigo del concurso para consultar sus ganadores:");
        String cod = todo.nextLine();
        Concurso objetivo = encontrarConcurso(Integer.valueOf(cod));
        System.out.println();
        System.out.println("Se consultara el " + objetivo);
        objetivo.ganadores();
        administrarConcurso();
    }
    

    public static void administrarConcurso(){
        //Método que administra todos los menús asociados a los objetos tipo Concurso//
        System.out.println("------------------Concursos------------------");
        for (Concurso conc : listaConcursos) {
            System.out.println(conc);
        }
        System.out.println();
        System.out.println("Crear concurso (1)\nInscribir participante (2)\nGandaores pasados(3)\nRegresar al Menu Principal (4)\nEliga una de las opciones del menu Concursos:");
        //Scanner input = new Scanner(System.in);
        int entrada_user = todo.nextInt();
        todo.nextLine();
        switch (entrada_user) {
            case 1:
                crearConcurso();
                todo.nextLine();
                break;

            case 2:
                inscribirParticipante();
                todo.nextLine();
                break;
            
            case 3:
                ganadoresPasados();
                todo.nextLine();
                break;

            case 4:
                regresarMenuPrincipal();
                break;
        }
        //input.close();
    }

    public static void administrarDueños() {
        //Método que administra todos los menús asociados a los objetos tipo Dueño//
        System.out.println("------------------Dueños------------------");
        for (Dueño d : listaDueños) {
            System.out.println(d);
        }
        System.out.println();
        System.out.println("Crear dueño (1)\nEditar dueño (2)\nRegresar al Menu Principal (3)\nEliga una de las opciones del menu Dueños:");
        int entrada_user = todo.nextInt();
        todo.nextLine();
        switch (entrada_user) {
            case 1:
                crearDueño();
                todo.nextLine();
                break;

            case 2:
                editarDueño();
                todo.nextLine();
                break;

            case 3:
                regresarMenuPrincipal();
                todo.nextLine();
                break;
        }
    }

    public static void crearMascota() {
        //Método que pide al usuario ingresar por teclado toda la informacion necesaria para crear una nueva mascota y la añade a la base de datos//
        System.out.println("Creacion de una nueva mascota:");
        System.out.println("Ingrese el nombre:");
        String name = todo.nextLine();
        System.out.println("Ingrese el tipo de mascota (Perro/Gato):");
        String type = todo.nextLine();
        System.out.println("Ingrese la raza de su mascota:");
        String raza_pet = todo.nextLine();
        System.out.println("Ingrese la fecha de nacimiento (dd-mm-aaaa):");
        String nacimiento = todo.nextLine();
        //System.out.println("Ingrese el url de la foto:");
        //String link = todo.nextLine();
        System.out.println("------------------Dueños disponibles------------------");
        for (Dueño d : listaDueños) {
            System.out.println(d);
        }
        System.out.println();
        System.out.println("Ingrese la cedula del dueño de la mascota");
        String cedula_dueño = todo.nextLine();
        Dueño d = encontrarDueño(cedula_dueño);
        Mascota resultado = new Mascota(name, type, raza_pet, nacimiento, "", d);
        listaMascotas.add(resultado);
        System.out.println("La mascota ha sido agregada.");
        System.out.println();
        administrarMascotas();
    }

    public static void eliminarMascota() {
        //Método que pide al usuario ingresar por teclado el código de una Mascota existente en la base de datos y la elimina de esta pero no de ningun registro pasado//
        System.out.println("Escriba el codigo de la mascota a eliminar");
        String id_pet = todo.nextLine();
        Mascota m = encontrarMascota(Integer.valueOf(id_pet));
        int indice = listaMascotas.indexOf(m);
        listaMascotas.remove(indice);
        System.out.println("La mascota " + m.getNombre() + " fue eliminada.");
        System.out.println();
        administrarMascotas();


    }

    public static void administrarMascotas() {
        //Método que administra todos los menús asociados a los objetos tipo Mascota//
        System.out.println("------------------Mascotas------------------");
        for (Mascota m : listaMascotas) {
            System.out.println(m);
        }
        System.out.println();
        System.out.println("Crear mascota (1)\nEliminar mascota (2)\nRegresar al Menu Principal (3)\nEliga una de las opciones del menu Mascotas:");
        int entrada_user = todo.nextInt();
        todo.nextLine();
        switch (entrada_user) {
            case 1:
                crearMascota();
                todo.nextLine();
                break;

            case 2:
                eliminarMascota();
                todo.nextLine();
                break;

            case 3:
                regresarMenuPrincipal();
                todo.nextLine();
                break;
        }
    }

    public static void menuPrincipal(){
        //Método que se encarga de administrar todos los menús de la aplicación//
        System.out.println("------------------Menu Principal------------------\nAdministrar concursos (1)\nAdministrar dueños (2)\nAdministrar mascotas (3)\nEliga una de las opciones del Menu Principal:");
        int seleccion = todo.nextInt();
        switch (seleccion) {
            case 1:
                administrarConcurso();
                todo.nextLine();
                break;
            case 2:
                administrarDueños();
                todo.nextLine();
                break;
            case 3:
                administrarMascotas();
                todo.nextLine();
                break;
        }
    }



    public static void regresarMenuPrincipal() {
        //Método para regresar al menú principal desde cualquier otro menú//
        menuPrincipal();
    }

    public static void main(String[] args){

        cargarBaseDatos();
        System.out.println("datos cargados");
        //menuPrincipal();
        launch(args);
    }
}