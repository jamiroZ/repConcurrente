package TP5_SemaforosGenericos.ej6;

import java.util.concurrent.Semaphore;

public class TorreDeControl {//
    private Semaphore pista;
   
    private Semaphore aterrizaje;
    private Semaphore despege;
    
    private int espacio;//limite de espacio para aterrizar
    private int cont;
    public TorreDeControl(int espacio){
         this.espacio=espacio;//cantidad maxima de aviones que pueden aterrizar
         this.pista=new Semaphore(1);//pista libre (1) , pista ocupada (0) ,ya se que se prepara para aterrizar o despegar

    }
    public void IntentaAterrizar(){
       try {
            if (cont < espacio) {
                this.pista.acquire();//si la pista no esta ocupada que aterrize
            
                cont++;//incrementa el
                System.out.println("pista despejada preparando aterrizaje");
            }

       } catch (Exception e) {
        // TODO: handle exception
       } 

    }
    public void aterriza(){
        System.out.println("avion "+Thread.currentThread().getName()+" aterriza en la pista");
        this.pista.release();//despeja la pista para que otros aviones aterrizen o despegen
        
    }
    public void IntentaDespegar(){
        try {
             this.pista.acquire();//si la pista no esta ocupada que despege
             System.out.println("pista despejada , avion "+Thread.currentThread().getName()+" preparando despege ");
             espacio--;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void despegar(){

        System.out.println("avion "+Thread.currentThread().getName()+" despega de la pista");
        this.pista.release();//despeja la pista para que otros aviones la usen

    }
}
