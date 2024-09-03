package TP3_Sincronizacion.ej3;

public class Hamaca {
    public Hamaca(){

    }
    public synchronized void hamacar(){
        System.out.println(Thread.currentThread().getName() +"hamaca");
    }
}
