package modelo;

import java.io.Serializable;

public class Ciudad implements Serializable{
    private String nombre;
    private String provincia;
    private String codigo;
    
    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public String toString(){
        return this.getNombre();
    }
    
    // sobreescritura del metodo equals para poder comparar usando la variable nombre 
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;            
        }
        if (obj != null && obj instanceof Ciudad) {
            Ciudad comparacion = (Ciudad) obj;
            return nombre.equals(comparacion.nombre);       
        }
        return false;
    }

    public Ciudad(String nombre, String provincia) {
        short x  = (short) (100*Math.random()+1);
        this.nombre = nombre;
        this.provincia = provincia;
        this.codigo = nombre.substring(0, 2).toUpperCase() + x + provincia.substring(1,3).toUpperCase();
        
    }

}
