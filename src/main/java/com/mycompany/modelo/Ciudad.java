package com.mycompany.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Ciudad implements Serializable{
    private static final long serialVersionUID = 8615504351333945644L;
    private String nombre;
    private String provincia;
    private int codigo;
    private static int contador = 0;
    
    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public int getCodigo() {
        return codigo;
    }
    
    @Override
    public String toString(){
        return this.getNombre();
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
    // sobreescritura del metodo equals para poder comparar usando la variable nombre 
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
        final Ciudad other = (Ciudad) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    public static ArrayList<Ciudad> cargarCiudades(String ruta) {
        ArrayList<Ciudad> ciudades = new ArrayList<>();
       //leer la lista de ciudades del archivo csv
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                //System.out.println("=============");
                ///System.out.println(strCurrentLine);
                String[] linea = strCurrentLine.strip().split(",");
                Ciudad temp = new Ciudad(linea[1],linea[2]);
                temp.setCodigo(Integer.valueOf(linea[0]));
                ciudades.add(temp);
            }         
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        }
        return ciudades;
    }

    public Ciudad(String nombre, String provincia) {
        this.nombre = nombre;
        this.provincia = provincia;
        contador ++;
        this.codigo = contador;
        
    }

}
