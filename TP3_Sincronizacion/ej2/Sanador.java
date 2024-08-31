package TP3_Sincronizacion.ej2;

public class Sanador extends Thread{
    private Energia obj;//objeto compartido
    public Sanador(Energia obj){
         this.obj=obj;
    }
    public void run(){
        obj.sumarEnergia(3);
    }
}
