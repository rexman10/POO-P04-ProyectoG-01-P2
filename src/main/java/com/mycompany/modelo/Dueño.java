package com.mycompany.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.proyecto_poo_mascotas_fx_p2.Aplicacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex_
 */
public class Dueño extends Persona implements Person{
    private static final long serialVersionUID = -2735017251734884812L;
    private int codigo;
    private String apellidos;
    private String email;
    public static int contador = 0;

    public Dueño(int c){
        super();
        this.codigo = c;
    }

    public Dueño(String nombre, String apellidos, String direccion, String telefono, Ciudad ciudad, String email) {
        super(nombre,direccion,telefono,ciudad);
        contador ++;
        this.codigo = contador;
        this.apellidos = apellidos;
        this.email = email;
    }

    public Dueño(int codigo, String nombre, String apellidos, String direccion, String telefono, Ciudad ciudad, String email) {
        super(nombre,direccion,telefono,ciudad);
        this.codigo = codigo;
        this.codigo = contador;
        this.apellidos = apellidos;
        this.email = email;
    }

    public static ArrayList<Dueño> cargarDueños(String ruta){
        ArrayList<Dueño> duenios = new ArrayList<>();
       //leer la lista de Dueño del archivo csv
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            br.readLine();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                //System.out.println("=============");
                //System.out.println(strCurrentLine);
                String[] linea = strCurrentLine.strip().split(",");
                Dueño temp = new Dueño(linea[2],linea[1],linea[3],linea[4],Aplicacion.encontrarCiudad(linea[5]),linea[6]);
                temp.setCodigo(Integer.valueOf(linea[0]));
                //System.out.println(Integer.valueOf(linea[0]));
                duenios.add(temp);
            }         
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        }
        return duenios;
    }

    public static Dueño encontrarDueño(int codigo) {
        Dueño temp = null;
        //Recibe como parámetro el codigo de un dueno y lo busca en la base de datos, si lo encuentra retorna la ciudad en cuestion caso contrario retorna null//
        try (BufferedReader br = new BufferedReader(new FileReader("archivos/duenosP4.csv"))) {
            br.readLine();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                //System.out.println("======Metodo encontrar dueno======");
                //System.out.println(strCurrentLine);
                String[] linea = strCurrentLine.strip().split(",");
                int comparacion = Integer.valueOf(linea[0]);
                //System.out.println(comparacion);
                //System.out.println(Integer.valueOf(linea[0]));
                if (comparacion == codigo) {
                    //System.out.println("entro al if");
                    temp = new Dueño(Integer.valueOf(linea[0]));
                    temp = Aplicacion.listaDueños.get(Aplicacion.listaDueños.indexOf(temp));
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
    public Persona encontrarPersona(int id) {
        Dueño temp = null;
        //Recibe como parámetro el codigo de un dueno y lo busca en la base de datos, si lo encuentra retorna la ciudad en cuestion caso contrario retorna null//
        try (BufferedReader br = new BufferedReader(new FileReader("archivos/duenosP4.csv"))) {
            br.readLine();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                //System.out.println("======Metodo encontrar dueno======");
                //System.out.println(strCurrentLine);
                String[] linea = strCurrentLine.strip().split(",");
                int comparacion = Integer.valueOf(linea[0]);
                //System.out.println(comparacion);
                //System.out.println(Integer.valueOf(linea[0]));
                if (comparacion == codigo) {
                    //System.out.println("entro al if");
                    temp = new Dueño(Integer.valueOf(linea[0]));
                    temp = Aplicacion.listaDueños.get(Aplicacion.listaDueños.indexOf(temp));
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
    public ArrayList cargarListado(String ruta) {
        ArrayList<Dueño> duenios = new ArrayList<>();
       //leer la lista de Dueño del archivo csv
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            br.readLine();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                //System.out.println("=============");
                //System.out.println(strCurrentLine);
                String[] linea = strCurrentLine.strip().split(",");
                Dueño temp = new Dueño(linea[2],linea[1],linea[3],linea[4],Aplicacion.encontrarCiudad(linea[5]),linea[6]);
                temp.setCodigo(Integer.valueOf(linea[0]));
                //System.out.println(Integer.valueOf(linea[0]));
                duenios.add(temp);
            }         
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        }
        return duenios;
    }
    
    // sobreescritura del metodo equals para poder comparar usando la variable cedula  
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dueño other = (Dueño) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
    

    public String toString() {
        return this.getNombre() + " " + this.getApellidos();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion.getCalle1();
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

    public String getCredenciales() {
        return nombre + " " + apellidos;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombres(String nombres) {
        this.nombre = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
