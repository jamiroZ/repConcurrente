package ejercicioParcial.parcial22.ejercicio1;

import java.util.concurrent.Semaphore;

public class Parque {
    private int capacidad;
    private int contP=0;//contador gente dentro
    private int vecinoEsperando=0;
    private boolean vecinoPref=false;//si hay un vecino esperando cambia a true
    
    public Parque(int capacidad){
         this.capacidad=capacidad;
    }
    public synchronized void entradaNormal(){
        try {
            while (this.contP == this.capacidad) {//esta lleno espera
                  
                 while(this.vecinoPref){//hay vecinos da prioridad
                   
                     System.out.println("espera "+Thread.currentThread().getName()+"hay vecinos esperando ");
                     this.wait();
                 }
                 System.out.println("espera "+Thread.currentThread().getName()+" esta lleno el parque ");
                 this.wait();
            }
               
            System.out.println(Thread.currentThread().getName()+" entra al parque ");
            this.contP++;//entra 1
     
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
    public synchronized void entradaPreferente(){
        try {
               while(this.contP == this.capacidad){
                    System.out.println(" espera "+Thread.currentThread().getName()+" espera el parque esta lleno ");
                    this.vecinoPref=true;
                    this.wait();//espera esta lleno
               }
          
               System.out.println(Thread.currentThread().getName()+" entra al parque ");
               this.contP++;//entra 1
               this.vecinoPref=false;//ya no hay vecino esperando notifica
               this.notifyAll();//notifica que no hay vecinos esperando
        } catch (Exception e) {
            // TODO: handle exception
        }
          
     }
    public synchronized void salir(boolean preferencia){  
        System.out.println(Thread.currentThread().getName()+" sale del parque ");
        this.contP--;//libera espacio para que entren otros
        this.notifyAll();
    }
}
