package TP8.ej1;

import java.util.concurrent.Semaphore;

public class Comedero {
     private Semaphore espacio;

     private int mostradorAlmuerzo=5;
     private Semaphore abridores;

     private int mostradorPostre=3;
     public Comedero(int capacidad){
           this.espacio=new Semaphore(capacidad);
           this.abridores=new Semaphore(10);
     }
     public void entrarComedero(){
         try {
            espacio.acquire();//entra al comedor
            System.out.println(Thread.currentThread().getName()+" entra al comedor");
         } catch (Exception e) {
            // TODO: handle exception
         }
     }
   public synchronized void tomarBandeja(){
      try {
         while(this.mostradorAlmuerzo==0){
            this.wait();//espera a que haya espacio en algun mostrador
         }
         System.out.println(Thread.currentThread().getName()+" toma la bandeja");
         this.mostradorAlmuerzo++;//toma bandeja 
         
      } catch (Exception e) {
         // TODO: handle exception
      }
   }
   public synchronized void dejarBandeja(){ 
          System.out.println(Thread.currentThread().getName()+" dejo la bandeja"); 
          this.mostradorAlmuerzo--;//toma bandeja 
          this.notifyAll();//avisa a los que estan esperando
   }
   public void abrirGaseosa(){
      try {
         this.abridores.acquire();
          System.out.println(Thread.currentThread().getName()+" abre gaseosa");
         this.abridores.release();
      } catch (Exception e) {
         // TODO: handle exception
      }
   }
   public synchronized void irMostradorPostre(){
        try {
            while(this.mostradorPostre==0){
               this.wait();//los 3 mostradores ocupados espera soldado
            }
            System.out.println(Thread.currentThread().getName()+" entra al mostradorPostres");
            this.mostradorPostre--;//un mostrador ocupado
        } catch (Exception e) {
            // TODO: handle exception
        }
   }
   public synchronized void salirMostradorPostre(){
          System.out.println(Thread.currentThread().getName()+" sale del mostradorPostres");
          this.mostradorPostre++;//sale del mostrador
          this.notify();
   }
   public  void  salirComedero(){  
         System.out.println(Thread.currentThread().getName()+" sale del comedor");
         espacio.release();//libera espacio para otro soldado
         
   }
}
