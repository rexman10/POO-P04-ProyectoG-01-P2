package modelo;

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
public class Mascota {
    private String nombre;
    private String tipoMascota;
    private String raza;
    private Calendar fechaNacimiento;
    private String urlFoto;
    private Dueño dueño;
    private String codigo;

    public Mascota(String codigo) {
        this.codigo = codigo;
    }

    public Mascota(String nombre, String tipoMascota, String raza, String fechaNacimiento, String urlFoto, Dueño dueño) {
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.raza = raza;
        String[] datos = fechaNacimiento.split("-", 3);
        int dia = Integer.valueOf(datos[0]);
        int mes = Integer.valueOf(datos[1]);
        int anio = Integer.valueOf(datos[2]);
        Calendar birth = new GregorianCalendar(anio, mes, dia);
        this.fechaNacimiento = birth;
        this.urlFoto = urlFoto;
        this.dueño = dueño;
        short x  = (short) (1000*Math.random()+1);
        codigo = tipoMascota.toUpperCase() + x + "-" + raza.substring(0,3).toUpperCase();
    }

    public void setCodigo(String code) {
        this.codigo = code;
    }

    // genera un codigo aleatorio que se concatena con los primeros tres caracteres de la variable raza 
    public void generarCodigo(Mascota m) {
        short x  = (short) (1000*Math.random()+1);
        codigo = this.tipoMascota.toUpperCase() + x + "-" + this.raza.substring(0,3).toUpperCase();
        this.setCodigo(codigo);
    }

    public String toString(){
        return "Nombre:" + this.getNombre() + " - Codigo:" + this.getCodigo() + " - Dueño:" + this.getDueño();
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

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public Dueño getDueño() {
        return dueño;
    }

    public String getCodigo() {
        return codigo;
    }
    
    
}
