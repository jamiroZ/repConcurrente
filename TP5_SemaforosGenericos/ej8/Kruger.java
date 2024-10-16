package TP5_SemaforosGenericos.ej8;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Kruger {
  
    private Semaphore mutex;
    //babuinos a la derecha o izquierda del acantilado
    private Semaphore cuerda_Izq;
    private Semaphore cuerda_Der;
    
    private int limite_Cuerda =5;//cantidad de babuinos que pueden cruzarla al mismo tiempo
    private int cruzado;//contador

    private boolean flag;
    public Kruger(){
   
        this.mutex=new Semaphore(1);//exclusion
        //5 babuinos pueden crusar al mismo tiempo
        this.cuerda_Der=new Semaphore(0);
        this.cuerda_Izq=new Semaphore(0);
        
        this.cruzado=0;
        this.flag=false;
    }
    public void cruzarCuerdaIzq(){
        try {
          cuerda_Izq.acquire();
             mutex.acquire();
               
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
        
            this.mutex.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public void cruzarCuerdaDer(){
        try {
            cuerda_Der.acquire();
               this.mutex.acquire();
            
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
             
            mutex.release();
            } catch (Exception e) {
            // TODO: handle exception
        }
    }
    //metodo para decidir quien arranca primero
    public void inicio(){
        try {
            this.mutex.acquire();
             //
             if(!flag){  
                //numero random entre 0 y 1
                //0:babuinos derechos arrancan
                //1:babuinos izquierdos arrancan
                 Random random=new Random();
                 int i= random.nextInt(2);

                 //System.out.println(i);
                 if(i==0){
                    
                    //System.out.println("d");  
                    //System.out.println(flag);
                    this.cuerda_Der.release(5);  
                                    
                 }else if(i==1){
                    //System.out.println("i");   
                    this.cuerda_Izq.release(5);    
                 }
                 flag=true;//para que ningun otro hilo acceda
            }
            this.mutex.release();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    
}
