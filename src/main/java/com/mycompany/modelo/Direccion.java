package com.mycompany.modelo;

public class Direccion {
    String calle1;
    String calle2;
    Ciudad ciudad;
    int numeroMz;
    int numeroSolar;
    
    public Direccion(String c1){
        this.calle1 = c1;
    }

    public String getCalle1(){
        return this.calle1;
    }
    
    public String getCalle2(){
        return this.calle2;
    }

    public Ciudad getCiudad(){
        return this.ciudad;
    }

    public int getMz(){
        return this.numeroMz;
    }

    public int getSolar(){
        return this.numeroSolar;
    }

    public void setCalle1(String c1){
        this.calle1 = c1;
    }
    
    public void setCalle2(String c2){
        this.calle2 = c2;
    }

    public void setCiudad(Ciudad city){
        this.ciudad = city;
    }

    public void setMz(int number){
        this.numeroMz = number;
    }

    public void setSolar(int number){
        this.numeroSolar = number;
    }
    
    @Override
    public String toString(){
        return this.calle1;
    }
}
