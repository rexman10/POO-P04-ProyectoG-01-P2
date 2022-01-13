package com.mycompany.modelo;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex_
 */
public class Premio implements Serializable{
    private int puesto;
    private String descripcion;
    private Auspiciante auspiciante;
    
    public Premio(int p, String d) {
        this.puesto = p;
        this.descripcion = d;
    }

    public Premio(int p, String d, Auspiciante auspiciante) {
        this.puesto = p;
        this.descripcion = d;
        this.auspiciante = auspiciante;
    
    }
    
    public void setAuspiciante(Auspiciante a) {
        this.auspiciante = a;
    }

    public int getPuesto() {
        return puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Auspiciante getAuspiciante() {
        return auspiciante;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
    
}
