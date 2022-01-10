package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex_
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Concurso implements Serializable{
    private String nombre;
    private Calendar fecha;
    private String temporal;
    private String hora;
    private Calendar fechaInicioInscrip;
    private Calendar fehcaFinInscrip;
    private Ciudad ciudad;
    private String lugar;
    private Auspiciante auspiciante;
    private String dirigido;
    private static int contador = 0;
    private int codigo;
    private ArrayList<Mascota> mascotasInscritas;
    private ArrayList<Mascota> listaGanadores;
    private ArrayList<Premio> listaPremios;

    public Concurso(int codigo) {
        this.codigo = codigo;
    }

    public Concurso(String nombre, Calendar fecha, String hora, Calendar fechaInicioInscrip, Calendar fehcaFinInscrip, Ciudad ciudad, String lugar, ArrayList<Premio> premios, Auspiciante auspiciante, String dirigido) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaInicioInscrip = fechaInicioInscrip;
        this.fehcaFinInscrip = fehcaFinInscrip;
        this.ciudad = ciudad;
        this.lugar = lugar;
        this.listaPremios = premios;
        this.auspiciante = auspiciante;
        this.dirigido = dirigido;
        contador ++;
        this.codigo = contador;
        for (Premio premio : premios) {
            premio.setAuspiciante(auspiciante);
        }
        ArrayList<Mascota> inscritos = new ArrayList<>();
        ArrayList<Mascota> ganadores = new ArrayList<>();
        mascotasInscritas = inscritos;
        listaGanadores = ganadores;
        this.temporal = Fechas.convert((GregorianCalendar) this.fecha);
    }

    // este metodo escoge tres mascotas al azar de la lista mascotas inscritas para poder posicionarlas como ganadores
    public void ganadores(){
        Random r = new Random();
        ArrayList<Mascota> l_masCopia = (ArrayList<Mascota>) mascotasInscritas.clone();
        int long_mascotas = mascotasInscritas.size();
        int i_primer_lugar = r.nextInt((long_mascotas-1));
        Mascota primer_lugar = l_masCopia.get(i_primer_lugar);
        l_masCopia.remove(primer_lugar);
        long_mascotas = l_masCopia.size();

        int i_segundo_lugar = r.nextInt(long_mascotas-1);
        Mascota segundo_lugar = l_masCopia.get(i_segundo_lugar);
        l_masCopia.remove(segundo_lugar);
        long_mascotas = l_masCopia.size();
        
        int i_tercer_luar = r.nextInt(long_mascotas-1);
        Mascota tercer_lugar = l_masCopia.get(i_tercer_luar);
        System.out.println(this.getPremios());
        System.out.println();
        System.out.println("Primer lugar: "+primer_lugar+"\nSegundo lugar: "+segundo_lugar+"\nTercer lugar: "+tercer_lugar);        
    }

    public String toString(){
        return "Concurso: " + this.getNombre() + " - Codigo: " + this.getCodigo();
    }

    public void inscribirMascota(Mascota m) {
        this.mascotasInscritas.add(m);
    }
    
    public String getNombre() {
        return nombre;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public String getTemporal() {
        return temporal;
    }

    public String getHora() {
        return hora;
    }

    public Calendar getFechaInicioInscrip() {
        return fechaInicioInscrip;
    }

    public Calendar getFehcaFinInscrip() {
        return fehcaFinInscrip;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public String getLugar() {
        return lugar;
    }

    public ArrayList<Premio> getPremios() {
        return listaPremios;
    }

    public Auspiciante getAuspiciantesLista() {
        return auspiciante;
    }

    public String getDirigido() {
        return dirigido;
    }

    public int getCodigo() {
        return codigo;
    }

    public ArrayList<Mascota> getListaConcursantes() {
        return this.mascotasInscritas;
    }

    public ArrayList<Mascota> getListaGanadores() {
        return this.listaGanadores;
    }

    public static ArrayList cargarConcursos(String ruta) {
        ArrayList<Concurso> concursos = new ArrayList<>();
        System.out.println("xxxxxxxxxxxxx");
       //leer la lista de personas del archivo serializado
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(ruta))) {
            concursos = (ArrayList<Concurso>) oi.readObject();
            System.out.println("=============");
            // System.out.println(concursos);
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        } catch (ClassNotFoundException  ex) {
            System.out.println("error class:"+ex.getMessage());
        }
        

        return concursos;
    }
}
