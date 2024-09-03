package TP3_Sincronizacion.ej5;

public class Surtidor {
    private int MAXIMO=100;//MAXIMO DE COMBUSTIBLE DEL SURTIDOR
    public Surtidor(){
    }
    public synchronized void usar(){
        System.out.println(Thread.currentThread().getName()+"cargando nafta");
    }
}
