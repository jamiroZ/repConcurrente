package TP3_Sincronizacion.ej5;

public class Vehiculo {
    private String modelo;
    private String marca;
    private int km;//kilometros recorridos
    public Vehiculo (String modelo,String marca,int km){
         this.modelo=modelo;
         this.km=km;
    }
    public String getMarca(){
        return this.marca;
    }
    public String getModelo(){
        return this.modelo;
    }
    public int getKm(){
        return this.km;
    }
    public void setMarca(String marca){
        this.marca=marca;
    }
    public void setmodelo(String modelo){
        this.modelo=modelo;
    }
    public void setKm(int km){
        this.km=this.km + km;
    }
    @Override
    public String toString(){
        return "marca: "+marca+" modelo: "+modelo+" km: "+km;
    }
}
