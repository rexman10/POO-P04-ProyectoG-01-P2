package com.mycompany.modelo;

import com.mycompany.modelo.Ciudad;
import com.mycompany.proyecto_poo_mascotas_fx_p2.Aplicacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class Auspiciante extends Persona{
    private String email;
    private String webPage;
    private static int contador = 0;
    private int codigo;

    public Auspiciante(int codigo) {
        this.codigo = codigo;
    }

    //la variable short x en el constructor genera un número al azar
    public Auspiciante(String nombre, String direccion, String telefono, Ciudad ciudad, String email, String webPage) {
        super(nombre,direccion,telefono,ciudad);
        this.email = email;
        this.webPage = webPage;
        contador ++;
        this.codigo = contador;
    }

    public static ArrayList<Auspiciante> cargarAuspiciantes(String ruta) {
        ArrayList<Auspiciante> auspiciantes = new ArrayList<>();
       //leer la lista de auspiciantes del archivo csv
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            br.readLine();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                //System.out.println("=============");
                //System.out.println(strCurrentLine);
                String[] linea = strCurrentLine.strip().split(",");
                Auspiciante temp = new Auspiciante(linea[1], linea[2], linea[3], Aplicacion.encontrarCiudad(linea[4]), linea[5], linea[6]);
                temp.setCodigo(Integer.valueOf(linea[0]));
                auspiciantes.add(temp);
            }         
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        }
        return auspiciantes;
    }

    // sobreescritura del metodo equals para poder comparar usando la variable codigo 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null &&  obj instanceof Auspiciante) {
            Auspiciante other = (Auspiciante) obj;
            return codigo == other.codigo;
        }
        
        return false;
    }

    public String toString() {
        return this.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public String getEmail() {
        return email;
    }

    public String getWebPage() {
        return webPage;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public static void setContador(int contador) {
        Auspiciante.contador = contador;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    
    
}
