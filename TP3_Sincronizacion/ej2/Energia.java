package TP3_Sincronizacion.ej2;

public class Energia {
    private int en=10;
    public Energia(){

    }
    public synchronized void sumarEnergia(int val){
           en=en+val;
    }
    public synchronized void restarEnergia(int rest){
          en=en-rest;
    }
    public int getEnergia(){
        return en;
    }
}
