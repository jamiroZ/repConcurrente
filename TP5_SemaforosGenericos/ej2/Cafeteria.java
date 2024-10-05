package TP5_SemaforosGenericos.ej2;

import java.util.concurrent.Semaphore;

public class Cafeteria {
    private Semaphore espacio;
    private Semaphore tomar;
    private Semaphore comer;
    public Cafeteria(){

         this.espacio=new Semaphore(2);

         this.tomar=new Semaphore(0);//un mozo
         this.comer=new Semaphore(0);//un cocinero
    }
    public boolean tomarAsiento(){
        boolean ret=false;
        try {
             ret=this.espacio.tryAcquire();
             if(ret){
                System.out.println(Thread.currentThread().getName()+" Se Sento ");
             }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ret;
    }
    public void dejarAsiento(){
        System.out.println(Thread.currentThread().getName()+" Dejo el asiento ");
        espacio.release();
    }
    //el empleado pide 
    public void tomarAlgo(){
        System.out.println("Empleado " + Thread.currentThread().getName() + " tomo asiento y pidio una bebida");
        this.tomar.release();//
    }
    public void comerAlgo(){
        System.out.println("Empleado " + Thread.currentThread().getName() + " tomo asiento y pidio algo para comer ");
        this.comer.release();//
    }

    //mozo atiende

    public void atenderEmpleado(){
    
        try {
             this.tomar.acquire();//si el mozo no esta ocupado atiende al empleado
             System.out.println("mozo atiende al empleado");
             
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
    public void servirBebida(){
        System.out.println("mozo le sirve la bebida");
        
    }
    
    //cocinero come
    public void servirEmpleado(){
        try {
            this.comer.acquire();//si el cocinero no esta ocupado atiende al empleado
            System.out.println("cocinero atiende al empleado");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void servirComida(){
        System.out.println("cocinero le sirve la comida");
       
    }
}
