package com.mycompany.modelo;

import com.mycompany.proyecto_poo_mascotas_fx_p2.Aplicacion;
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

    /*public String toString(){
        return "Nombre:" + this.getNombre() + " - Codigo:" + this.getCodigo() + " - Dueño:" + this.getIdDueño();
    }*/

    
    public static Mascota encontrarMascota(int codigo) {
        Mascota temp = null;
        //Recibe como parámetro el codigo de un dueno y lo busca en la base de datos, si lo encuentra retorna la ciudad en cuestion caso contrario retorna null//
        try (BufferedReader br = new BufferedReader(new FileReader("archivos/mascotas.csv"))) {
            br.readLine();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                //System.out.println("======Metodo encontrar Mascota======");
                //System.out.println(strCurrentLine);
                String[] linea = strCurrentLine.strip().split(",");
                int comparacion = Integer.valueOf(linea[0]);
                //System.out.println(comparacion);
                //System.out.println(Integer.valueOf(linea[0]));
                if (comparacion == codigo) {
                    //System.out.println("entro al if");
                    temp = new Mascota(Integer.valueOf(linea[0]));
                    temp = Aplicacion.listaMascotas.get(Aplicacion.listaMascotas.indexOf(temp));
                    //System.out.println("encontrar duenio "+temp);
                }
            }
            br.close();
        } catch (Exception e) {
            e.getMessage();
        }
        //System.out.println(temp);
        return temp;
    }

    
    
    @Override
    public String toString() {
        return nombre;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
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

    public int getId_dueño() {
        return id_dueño;
    }

    public Dueño getDuenio() {
        return duenio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public void setId_dueño(int id_dueño) {
        this.id_dueño = id_dueño;
    }

    public void setDuenio(Dueño duenio) {
        this.duenio = duenio;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public static void setContador(int contador) {
        Mascota.contador = contador;
    }

    
      
    
}
