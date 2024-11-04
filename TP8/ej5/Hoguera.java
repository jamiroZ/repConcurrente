package TP8.ej5;

import java.util.concurrent.Semaphore;

public class Hoguera {
    private int cantPorciones;
    private int cont=0;
    private Semaphore cocinar;
    private  Semaphore comer;
    private Semaphore espera;
    public Hoguera(int cantPorciones){
        this.cantPorciones = cantPorciones;
        this.comer = new Semaphore(1);
        this.cocinar=new Semaphore(0);
        this.espera=new Semaphore(0);
    }
    public void dormir(){
         try {
            this.cocinar.acquire();//duerme hasta que le avisen que cocine
            System.out.println(Thread.currentThread().getName()+" COCINA ");
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
    public void llenarOlla(){
          try {
            System.out.println(Thread.currentThread().getName()+" llena la olla");
            this.cont=cantPorciones;//llena la olla 
            this.espera.release();//avisa que la olla esta llena
          } catch (Exception e) {
            // TODO: handle exception
          }
    }
    public void servirDeOlla(){
          try {
            this.comer.acquire();//intenta servise de la olla
                if(this.cont==0){//olla vacia 
                     System.out.println("olla vacia avisa al cocinero");
                     this.cocinar.release();// avisa al cocinero
                     this.espera.acquire();//espera a que llene la olla
                }
                System.out.println(Thread.currentThread().getName()+" se sirve de la olla");
                this.cont--;//toma una porcion
                
          } catch (Exception e) {
            // TODO: handle exception
          }
          this.comer.release();//avisa para que otro pueda servirse
    }
}
