package com.mycompany.modelo;
import com.mycompany.modelo.Concurso;
import com.mycompany.modelo.Mascota;
import com.mycompany.modelo.Premio;

public class Ganador {
    private int puesto;
    private String mascota;
    private String premio;

    public Ganador(int puesto, String m, String premio) {
        this.puesto = puesto;
        this.mascota = m;
        this.premio = premio;
    }

    public int getPuesto() {
        return puesto;
    }

    public String getMascota() {
        return mascota;
    }

    public String getPremio() {
        return premio;
    }
}
