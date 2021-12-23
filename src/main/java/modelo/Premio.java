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
public class Premio {
    private String premio1;
    private String premio2;
    private String premio3;
    private Auspiciante auspiciante;
    
    public Premio(String premio1, String premio2, String premio3) {
        this.premio1 = premio1;
        this.premio2 = premio2;
        this.premio3 = premio3;
    }

    public Premio(String premio1, String premio2, String premio3, Auspiciante auspiciante) {
        this.premio1 = premio1;
        this.premio2 = premio2;
        this.premio3 = premio3;
        this.auspiciante = auspiciante;
    }
    

    public void setAuspiciante(Auspiciante a) {
        this.auspiciante = a;
    }

    public String getPremio1() {
        return premio1;
    }

    public String getPremio2() {
        return premio2;
    }

    public String getPremio3() {
        return premio3;
    }

    public Auspiciante getAuspiciante() {
        return auspiciante;
    }

    @Override
    public String toString() {
        return "Primer lugar: " + premio1 + ", auspiciante " + auspiciante.getNombre() + "\n" + "Segundo lugar: " + premio2 + ", auspiciante " + auspiciante.getNombre() + "\n" + "Tercer lugar: " + premio3 + ", auspiciante " + auspiciante.getNombre();
    }
    
    
}
