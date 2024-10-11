package TP5_SemaforosGenericos.ej8;

import java.util.concurrent.Semaphore;

public class Kruger {
  
    private Semaphore mutex;
    //babuinos a la derecha o izquierda del acantilado
    private Semaphore cuerda_Izq;
    private Semaphore cuerda_Der;
  
    private int limite_Cuerda =5;//cantidad de babuinos que pueden cruzarla al mismo tiempo
    private int cruzado;//contador
    public Kruger(){
   
        this.mutex=new Semaphore(1);//exclusion
        //5 babuinos pueden crusar al mismo tiempo
        this.cuerda_Der=new Semaphore(5);
        this.cuerda_Izq=new Semaphore(5);
        
        this.cruzado=0;
    }
    public void cruzarCuerdaIzq(){
        try {
          cuerda_Izq.acquire();
             mutex.acquire();
               if(cruzado==0){
                    cuerda_Der.acquire(limite_Cuerda);
               }
               cruzado++;
           
               System.out.println("babuino izquierda "+Thread.currentThread().getName()+" se colgo de la cuerda");
              mutex.release();
            } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void bajarCuerdaIzq(){
        
        try {
            mutex.acquire();
                cruzado--;
                if(cruzado==0){
                    cuerda_Der.release(limite_Cuerda);
                }
     
                System.out.println("babuino izquierda "+Thread.currentThread().getName()+" Bajo de la cuerda");
                this.cuerda_Izq.release();
            this.mutex.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public void cruzarCuerdaDer(){
        try {
            cuerda_Der.acquire();
               this.mutex.acquire();
                 if(cruzado==0){
                     cuerda_Izq.acquire(limite_Cuerda);
                 }
                 cruzado++;
                 System.out.println("babuino derecho "+Thread.currentThread().getName()+" se colgo de la cuerda");
           
               this.mutex.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void bajarCuerdaDer(){
       
        try {
            mutex.acquire();
              cruzado--;
              if(cruzado==0){
                cuerda_Izq.release(limite_Cuerda);
              }

              System.out.println("babuino derecho "+Thread.currentThread().getName()+" Bajo de la cuerda");
              cuerda_Der.release();
            mutex.release();
            } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
