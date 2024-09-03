package TP3_Sincronizacion.ej3;

public class Plato {
    public Plato(){

    }
    public synchronized void comer(){
        System.out.println(Thread.currentThread().getName() +"juega");
    }
}
