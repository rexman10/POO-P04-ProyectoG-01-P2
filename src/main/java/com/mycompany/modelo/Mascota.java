package com.mycompany.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex_
 */
public class Mascota implements Serializable{
    private static final long serialVersionUID = 6862198783941299573L;
    private String nombre;
    private String tipoMascota;
    private String raza;
    private String fechaNacimiento;
    private String urlFoto;
    private int id_dueño;
    private Dueño duenio;
    private int codigo;
    private static int contador = 0;

    public Mascota(int codigo) {
        this.codigo = codigo;
    }
    
    public static int getContador() {
        return contador;
    }

    public Mascota(String nombre, String tipoMascota, String raza, String fechaNacimiento, String urlFoto, int id_dueño) {
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.urlFoto = urlFoto;
        this.id_dueño = id_dueño;
        //this.dueño = Dueño.encontrarDueño(id_dueño);
        contador ++;
        this.codigo = Mascota.getContador();
        
    }

    public static ArrayList<Mascota> cargarMascotas(String ruta) {
        ArrayList<Mascota> mascotas = new ArrayList<>();
       //leer la lista de mascotas del archivo csv
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String strCurrentLine;
            br.readLine();
            while ((strCurrentLine = br.readLine()) != null) {
                //System.out.println("=============");
                //System.out.println(strCurrentLine);
                String[] linea = strCurrentLine.strip().split(";");
                Mascota temp = new Mascota(linea[1],linea[2],linea[3],linea[4],linea[5],Integer.valueOf(linea[6]));
                temp.setCodigo(Integer.valueOf(linea[0]));
                Dueño d = Dueño.encontrarDueño(Integer.valueOf(linea[6]));
                temp.setDuenio(d);
                //System.out.println("leido en cargar mascota" + d);
                //System.out.println(temp);
                mascotas.add(temp);
                //System.out.println(mascotas);
            }         
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        }
        //System.out.println(mascotas);
        return mascotas;
    }

    public String toString(){
        return "Nombre:" + this.getNombre() + " - Codigo:" + this.getCodigo() + " - Dueño:" + this.getIdDueño();
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Dueño getDuenio() {
        return duenio;
    }

    public void setDuenio(Dueño d) {
        this.duenio = d;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public String getRaza() {
        return raza;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public int getIdDueño() {
        return id_dueño;
    }

    public int getCodigo() {
        return codigo;
    }
      
    
}
