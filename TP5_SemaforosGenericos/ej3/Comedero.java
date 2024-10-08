package TP5_SemaforosGenericos.ej3;

import java.util.concurrent.Semaphore;

public class Comedero {
      private Semaphore mutex;//cantidad de perros/gatos que pueden comer
      private Semaphore hayPerro;//si hay perros dentro del comedero se instancia en 0
      private Semaphore hayGato;//si hay gatos se instancia en 0
      
      private char mascota=' ';//
      private int cant;
      public Comedero(int cant){//CONSTRUCTOR
            this.mutex=new Semaphore(cant);//tal cantidad de perros o gatos pueden entrar
            //
            this.cant=cant;
           
      }

      public void comerPerro() {
          try {
            
            while(mascota =='g'){//si hay un gato que esperen afuera del comedor         
                 // System.out.println("perro "+mascota);
            }
            this.mascota='p';//hay perros ahora que esperen afuera
            this.mutex.acquire();//si hay espacio que entre 
            
              
            System.out.println("perro "+Thread.currentThread().getName()+" entro a comer");
             
          } catch (Exception e) {
            // TODO: handle exception
          }
      }
      public void salirPerro(){    
          System.out.println("perro "+Thread.currentThread().getName()+" salio del comedor");
          this.mutex.release();
          if(this.mutex.tryAcquire(cant)){//si fue el ultimo perro en salir ,que libere todos para que pueda entrar gatos
             this.mascota='g';
             this.mutex.release(cant);
          }else{//si no fue el ultimo en salir que libere solo un permiso
             this.mutex.release();
          }
         
          
      }
      public void comerGato() {
          try {

            while(mascota =='p'){//si hay un gato que esperen afuera del comedor
                //System.out.println("gato "+mascota);
            } 

            this.mascota='g';//hay gatos ahora que esperen afuera 
            this.mutex.acquire();//si hay espacio que entre ,sino que espere
                
            System.out.println("gato "+Thread.currentThread().getName()+" entro a comer"); 
            
          } catch (Exception e) {
           // TODO: handle exception
          }
      }
      public void salirGato() {
           System.out.println("gato "+Thread.currentThread().getName()+" salio del comedor"); 
         
           this.mutex.release();
           if(this.mutex.tryAcquire(cant)){//si fue el ultimo gato en salir ,que libere todos para que pueda entrar gatos
               this.mascota='p';
               this.mutex.release(cant);
           }else{//si no fue el ultimo en salir que libere solo un permiso
               this.mutex.release();
           }    
      }

}

