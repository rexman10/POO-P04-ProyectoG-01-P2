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
public class Dueño extends Persona{
    private String cedula;
    private String apellidos;
    private String email;

    public Dueño(String c){
        super();
        this.cedula = c;
    }

    public Dueño(String cedula, String nombre, String apellidos, String direccion, String telefono, Ciudad ciudad, String email) {
        super(nombre,direccion,telefono,ciudad);
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.email = email;
    }

    
    // sobreescritura del metodo equals para poder comparar usando la variable cedula
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null &&  obj instanceof Dueño) {
            Dueño other = (Dueño) obj;
            return cedula.equals(other.cedula);
        }
        
        return false;
    }

    public String toString(){
        return this.getNombre() + " con cedula " + this.getCedula();
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
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

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombres(String nombres) {
        this.nombre = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public void setEmail(String email) {
        this.email = email;
    }
    

}
