package TP3_Sincronizacion.ej3;

public class Rueda {
    public Rueda(){

    }
    public synchronized void jugar(){
          System.out.println(Thread.currentThread().getName() +"juega");
    }
}