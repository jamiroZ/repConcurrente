package TP4_SemaforosBinarios.ej7;

import java.util.concurrent.Semaphore;

public class Confiteria {//OBJETO COMPARTIDO
      private Semaphore espacio;
      private Semaphore servir;
      private Semaphore comer;
      public Confiteria(){//CONSTRUCTOR VACIO
           this.espacio=new Semaphore(1);//SI HAY ESPACIO TOMA ASIENTO ESPERA QUE LO ATIENDAN
           this.servir=new Semaphore(0);//EL MOZO LO ATIENDE Y LE SIRVE 
           this.comer=new Semaphore(0);//SI EL MOZO LE SIRVE LA COMIDA,COME Y SE VA DEJANDO EL AISENTO LIBRE
      }
      public boolean tomarAsiento(){
          boolean ret=false;
          try {
               //SI HAY ESPACIO EL EMPLEADO SE SIENTA A ESPERAR SER ATENDIDO
               this.espacio.acquire();
               System.out.println("Empleado "+Thread.currentThread().getName()+" tomo asiento ");
               ret=true;
               
          } catch (Exception e) {
            // TODO: handle exception
          }
           //SI TOMO ASIENTO EL EMPLEADO AVISA AL MOZO PARA QUE LO ATIENDA
           this.servir.release();// 
          
          return ret;
      }
      public boolean atenderEmpleado(){
           boolean ret=false;
           try {
                //
                this.servir.acquire();
                System.out.println("el mozo atiende al empleado ");
           } catch (Exception e) {
            // TODO: handle exception
           }
           this.comer.release();
           return ret;
      }
      public void servirComida(){
           try {
                //
                this.comer.tryAcquire();
                System.out.println("mozo sirvio la comida");
           } catch (Exception e) {
            // TODO: handle exception
           }
           this.servir.release();
      }
      public void dejarAsiento(){
          try {
                this.servir.acquire();
                System.out.println(Thread.currentThread().getName()+" comio y libero el asiento ");
          } catch (Exception e) {
            // TODO: handle exception
          }
          this.espacio.release();
      }
}
