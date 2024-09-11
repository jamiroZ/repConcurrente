package TP4_SemaforosBinarios.ej6;

import java.util.concurrent.Semaphore;

public class Taxi {//OBJETO COMPARTIDO 
    private Semaphore subir;
    private Semaphore llegar;
    private String patente;
    public Taxi(String patente){
        this.subir=new Semaphore(1);//
        this.llegar=new Semaphore(0);
        //
        this.patente=patente;
    }
    //METODOS QUE USA EL HILO PASAJERO
    public void subirTaxi(){
        try {
            this.subir.acquire();
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.llegar.release();
    }
    public void bajarTaxi(){
        try{
            this.subir.acquire();
        } catch (Exception e) {
            // TODO: handle exception
        }
       
    }
    //METODOS QUE USA EL HILO TAXI
    public void arrancar(){
        try {
            this.llegar.acquire();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
    public void detener(){
        this.subir.release();
    }
}
