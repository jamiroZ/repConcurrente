package TP5_SemaforosGenericos.ej8;

import java.util.concurrent.Semaphore;

public class Kruger {
    private Semaphore cuerda;
    private Semaphore mutex;
    //babuinos a la derecha o izquierda del acantilado
    private Semaphore babuinosIzq;
    private Semaphore babuinosDer;
  

    //contador de cuantos pasaron de cada lado
    private int pasaronIzq;
    private int pasaronDer;
    
    private int pasaronIzqTotal=0;
    private int pasaronDerTotal=0;
    
    private int permisos_Babuinos=15;
    public Kruger(){
        //5 babuinos pueden crusar al mismo tiempo
        this.cuerda=new Semaphore(5);
        this.mutex=new Semaphore(1);
        
        this.babuinosDer=new Semaphore(permisos_Babuinos);
        this.babuinosIzq=new Semaphore(permisos_Babuinos);
        
        this.pasaronIzq=0;
        this.pasaronDer=0;
    
    }
    public void cruzarCuerdaIzq(){
        try {
             this.mutex.acquire();
                 this.babuinosDer.tryAcquire(permisos_Babuinos);
                 this.cuerda.acquire();
                 pasaronIzq++;

            this.mutex.release();
            System.out.println("babuino izquierda "+Thread.currentThread().getName()+" se colgo de la cuerda");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void bajarCuerdaIzq(){
        System.out.println("babuino izquierda "+Thread.currentThread().getName()+" Bajo de la cuerda");
        try {
            this.mutex.acquire();
                if(pasaronIzq==5){
                     //aumento el contador de babuinos que pasar del lado derecho a izquierdo
                     this.pasaronIzqTotal=pasaronIzqTotal+pasaronIzq;
                     pasaronIzq=0;
                     
                     this.babuinosDer.release(permisos_Babuinos);
                }
                
            this.mutex.release();
        } catch (Exception e) {
            // TODO: handle exception
        }this.cuerda.release();
    }



    public void cruzarCuerdaDer(){
        try {
            this.mutex.acquire();//esclucion 
                 //bloqueo a los babuinos del otro lado
                 this.babuinosIzq.tryAcquire(permisos_Babuinos);
                 this.cuerda.acquire();//si hay espacio se cuelga de la cuerda ,hasta 5 
                 pasaronDer++;
            this.mutex.release();
                 System.out.println("babuino derecho "+Thread.currentThread().getName()+" se colgo de la cuerda");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void bajarCuerdaDer(){
        System.out.println("babuino derecho "+Thread.currentThread().getName()+" Bajo de la cuerda");
        try {
            this.mutex.acquire();
                if(pasaronDer==5){
                     //aumento el contador de babuinos que pasar del lado derecho a izquierdo
                     this.pasaronDerTotal=pasaronDerTotal+pasaronDer;
                     pasaronDer=0;
                     this.babuinosIzq.release(permisos_Babuinos);
                }
                
            this.mutex.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.cuerda.release();
    }
    
}
