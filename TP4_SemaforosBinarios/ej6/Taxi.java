
package TP4_SemaforosBinarios.ej6;

import java.util.concurrent.Semaphore;

public class Taxi {//OBJETO COMPARTIDO 
    private Semaphore subir;
    private Semaphore conducir;
    private Semaphore bajar;
    private String patente;

    public Taxi(String patente){
        this.subir=new Semaphore(1);//Pasajero intenta subir al taxi
        this.conducir=new Semaphore(0);//si el Pasajero se subio el taxista arranca a conducir
        this.bajar=new Semaphore(0);//termino el viaje se detiene y baja el pasajero
        //
        this.patente=patente;
    }
    //METODOS QUE USA EL HILO PASAJERO
    public boolean subirTaxi(){//Pasajero se intenta subir al taxi
        boolean ret=false;
        
        try {
            this.bajar.release();
            if(this.subir.tryAcquire()){//si se sube : 0
                System.out.println("pasajero "+Thread.currentThread().getName()+" se subio al taxi "+patente);
                ret=true;//retorna verdadero y deja de caminar
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        if(ret){//si se subio (tomo el semaforo)
            this.conducir.release();//se despierta el taxista
        }
        return ret;
    }
    public void arrancar(){//Se subio un pasajero que arranque el viaje
        try {
            this.conducir.acquire();//arranca el viaje
            System.out.println("taxi "+Thread.currentThread().getName()+" arranco el viaje "+patente);
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.bajar.release();//viaja
    }
    public void detener(){//llego al destino se detiene y duerme
        try {
            this.bajar.acquire();//llego al destino 
            System.out.println("taxi "+Thread.currentThread().getName()+" llego al destino "+patente);
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.subir.release();//se baja el psajero
    } 
 
}
