package modelo;

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
import java.util.Random;

public class Concurso implements Serializable{
    private String nombre;
    private Calendar fecha;
    private int hora;
    private Calendar fechaInicioInscrip;
    private Calendar fehcaFinInscrip;
    private Ciudad ciudad;
    private String lugar;
    private Premio premios;
    private Auspiciante auspiciante;
    private String dirigido;
    private static int contador = 0;
    private int codigo;
    private ArrayList<Mascota> mascotasInscritas;
    private ArrayList<Mascota> listaGanadores;

    public Concurso(int codigo) {
        this.codigo = codigo;
    }

    public Concurso(String nombre, Calendar fecha, int hora, Calendar fechaInicioInscrip, Calendar fehcaFinInscrip, Ciudad ciudad, String lugar, Premio premios, Auspiciante auspiciantes, String dirigido) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaInicioInscrip = fechaInicioInscrip;
        this.fehcaFinInscrip = fehcaFinInscrip;
        this.ciudad = ciudad;
        this.lugar = lugar;
        this.premios = premios;
        this.auspiciante = auspiciantes;
        this.dirigido = dirigido;
        contador ++;
        this.codigo = contador;
        ArrayList<Mascota> inscritos = new ArrayList<>();
        ArrayList<Mascota> ganadores = new ArrayList<>();
        mascotasInscritas = inscritos;
        listaGanadores = ganadores;
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

    public int getHora() {
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

    public Premio getPremios() {
        return premios;
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
}
