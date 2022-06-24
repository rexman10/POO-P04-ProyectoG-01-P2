package com.mycompany.modelo;

import java.util.ArrayList;

public class Organizador implements Person{
    private String cedula;
    private Concurso concurso;

    public Organizador(String c, Concurso conc){
        this.cedula = c;
        this.concurso = conc;
    }

    @Override
    public Persona encontrarPersona(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList cargarListado(String ruta) {
        // TODO Auto-generated method stub
        return null;
    }


}