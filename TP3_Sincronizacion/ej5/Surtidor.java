package TP3_Sincronizacion.ej5;

public class Surtidor {
    private int maximo=100;//MAXIMO DE COMBUSTIBLE DEL SURTIDOR
    public Surtidor(){
    }
    public synchronized void usar(){
        System.out.println(Thread.currentThread().getName()+" cargando nafta");
        try{
             maximo=maximo-30;
             Thread.sleep(500);
        
        }catch(InterruptedException e){

        }
        System.out.println(Thread.currentThread().getName()+" termino de cargar nafta");
    }
}
