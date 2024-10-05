package TP5_SemaforosGenericos.ej3;

import java.util.concurrent.Semaphore;

public class Comedero {
      private Semaphore mutex;//cantidad de perros/gatos que pueden comer
      private Semaphore hayPerro;//si hay perros dentro del comedero se instancia en 0
      private Semaphore hayGato;//si hay gatos se instancia en 0
      private Semaphore control; // controla que entre uno o otro ,es binario
      public Comedero(int cant){
            this.mutex=new Semaphore(cant);//tal cantidad de perros o gatos pueden entrar
            //estan los 2 en 1 porque al inicio no hay ni perros ni gatos 
            this.hayPerro=new Semaphore(2);
            this.hayGato=new Semaphore(2);
            
            //
            this.control=new Semaphore(1);
      }
      public void comerPerro() throws InterruptedException{
          try {

              this.control.tryAcquire();

              this.hayPerro.acquire();//entra un perro
             
              this.mutex.acquire();//si hay espacio intenta entrar ,sino espera afuera
           
              System.out.println("perro "+Thread.currentThread().getName()+" entro a comer");
             
          } catch (Exception e) {
            // TODO: handle exception
          }
      }
      public void salirPerro(){    
          System.out.println("perro "+Thread.currentThread().getName()+" salio del comedor");
          this.hayPerro.release();//sale el perro
          //si fue el ultimo perro en salir que le toque a los gatos
          if(this.hayPerro.tryAcquire(2)){
                 try {
                     this.control.release();
                     this.hayGato.release(2);
                 } catch (Exception e) {
                  // TODO: handle exception
                 }
                 
          }
          this.mutex.release();//libera y llena un plato del comedero
          
      }
      public void comerGato() {
          try {
             this.control.tryAcquire();

             this.hayGato.acquire();//entro un gato 
             
             this.mutex.acquire();//si hay espacio intenta entrar ,sino espera afuera   
               
             System.out.println("gato "+Thread.currentThread().getName()+" entro a comer"); 

          } catch (Exception e) {
           // TODO: handle exception
          }
      }
      public void salirGato() {
           
           System.out.println("gato "+Thread.currentThread().getName()+" salio del comedor"); 
             
           this.hayGato.release();//sale el perro
           //si fue el ultimo gato en salir que le toque a los gatos
           if(this.hayGato.tryAcquire(2)){
                  try {
                      this.control.release();
                      this.hayPerro.release(2);
                  } catch (Exception e) {
                   // TODO: handle exception
                  }
                  
           }
           this.mutex.release(); 
      }
}

