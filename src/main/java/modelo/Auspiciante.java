package modelo;

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
    private String codigo;

    public Auspiciante(String codigo) {
        this.codigo = codigo;
    }

    //la variable short x en el constructor genera un número al azar
    public Auspiciante(String nombre, String direccion, String telefono, Ciudad ciudad, String email, String webPage) {
        super(nombre,direccion,telefono,ciudad);
        this.email = email;
        this.webPage = webPage;
        short x  = (short) (100*Math.random()+1);
        codigo = webPage.substring(4, 7).toUpperCase() + x;
    }

    

    // sobreescritura del metodo equals para poder comparar usando la variable codigo 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null &&  obj instanceof Auspiciante) {
            Auspiciante other = (Auspiciante) obj;
            return codigo.equals(other.codigo);
        }
        
        return false;
    }

    public String toString() {
        return "Nombre:" + this.getNombre() + " - Codigo:" + this.getCodigo();
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

    public String getCodigo() {
        return codigo;
    }
    
    
}
