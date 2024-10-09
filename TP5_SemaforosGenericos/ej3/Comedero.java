package TP5_SemaforosGenericos.ej3;

import java.util.concurrent.Semaphore;

public class Comedero {
      private Semaphore mutex;//cantidad de perros/gatos que pueden comer
      private Semaphore hayPerro;//si hay perros dentro del comedero se instancia en 0
      private Semaphore hayGato;//si hay gatos se instancia en 0
      private Semaphore espera;
      
      private Boolean inicio=true;
      private int cant;
      private int cont;//limite de animales que pueden entrar a comer de la misma especie
    

      public Comedero(int cant){//CONSTRUCTOR
            this.mutex=new Semaphore(cant);//tal cantidad de perros o gatos pueden entrar
           
            this.hayGato=new Semaphore(3);
            this.hayPerro=new Semaphore(3);
            
            this.cant=cant;//limite de animales que pueden entrar
            this.cont=0;//arranque
      }

      public void comerPerro() {
          try {
              this.hayGato.tryAcquire(3);    
              this.hayPerro.acquire();
              
              cont++;
              this.mutex.acquire();
              System.out.println("perro "+Thread.currentThread().getName()+" entro a comer"+cont);
             
          } catch (Exception e) {
            // TODO: handle exception
          }
      }
      public void salirPerro(){     
          System.out.println("perro "+Thread.currentThread().getName()+" salio del comedor"+cont);
          
          this.hayPerro.release();
          boolean flag= this.mutex.tryAcquire(cant-1);
          if(flag){//si entraron n perros ya enotonces que cambie a gatos
             System.out.println("perro "+Thread.currentThread().getName()+"xxxxxxxxxxxxx"+cont); 
            try { 
                cont=0;
                System.out.println(cont);
                this.hayPerro.acquire(3);//bloqueo si fue el ultimo perro en salir
                
                } catch (Exception e) {
              // TODO: handle exception
             } finally{
                  this.hayGato.release(cant);//libero para que gatos puedan entrar
             }
             this.mutex.release(cant);
          }else{
            this.mutex.release(cant-1);
          }  
    
      }
      public void comerGato() {
          try {
           
            this.hayGato.acquire();//tomo permiso de gato 
            cont++;
            this.hayPerro.tryAcquire(3);//tomo permiso de perro para que no puedan entrar  
            this.mutex.acquire();
            System.out.println("gato "+Thread.currentThread().getName()+" entro a comer"+cont); 
            
          } catch (Exception e) {
           // TODO: handle exception
          }
      }
      public void salirGato() {
          System.out.println("gato "+Thread.currentThread().getName()+" salio del comedor"+cont+" --"+cant); 
          
          this.hayGato.release();
          
          boolean flag=this.mutex.tryAcquire(cant-1);
          
          if(flag){//si entraron n gatos que cambie para que entren perros
              
            System.out.println("gato "+Thread.currentThread().getName()+" xxxxxxxxx"+cont);
            try {
                
                cont=0;   
                System.out.println(cont);
                this.hayGato.acquire(3);    
               
            } catch (Exception e) {
                // TODO: handle exception
            }finally{
                this.hayPerro.release(cant);//libero permiso para que puedan entrar perros
            }
            this.mutex.release(cant); 
          }else{
               this.mutex.release(cant-1);
          }
      }

}

