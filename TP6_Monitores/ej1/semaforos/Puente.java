package TP6_Monitores.ej1.semaforos;

import java.util.Random;
import java.util.concurrent.Semaphore;


public class Puente {
    private Semaphore cruzar;//exclusion
    private Semaphore lado_Sur;
    private Semaphore lado_Nor;
   
   
    private int capacidad;//cant autos que pueden pasar al mismo tiempo
    private int cruzando;//autos en el puente en un mismo momento
 
    private boolean flag;
    public Puente(int cant){
           this.capacidad=cant;
           this.cruzando=0;//contador
           //cant permisos para semaforos
           this.lado_Nor=new Semaphore(0);
           this.lado_Sur=new Semaphore(0);
           this.cruzar=new Semaphore(1);//EXCLUSION
           //
           this.flag=false;

    }
    public void cruzarPuenteNor(){
         try {
            this.lado_Nor.acquire();
             this.cruzar.acquire();
                 cruzando++;
                System.out.println("cruza el pente el auto "+Thread.currentThread().getName()+" por NORTE");
                
             this.cruzar.release();
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
    public void cruzarPuenteSur(){
        try {
            this.lado_Sur.acquire();
            this.cruzar.acquire();
                cruzando++;
                System.out.println("cruza el pente el auto "+Thread.currentThread().getName()+" por SUR ");
      
            this.cruzar.release();
        } catch (Exception e) {
           // TODO: handle exception
        }
    }
    public void salirPuenteNorte(){
        try {
            this.cruzar.acquire();
                 cruzando--;
                 if(cruzando==0){
                    this.lado_Nor.release(capacidad);
                 }
                 System.out.println("salio del pente el auto "+Thread.currentThread().getName()+" por el NORTE ");
            this.cruzar.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void salirPuenteSur(){
        try {
            this.cruzar.acquire();
               cruzando--;
               if(cruzando==0){
                   this.lado_Sur.release(capacidad);
               }
               System.out.println("salio del pente el auto "+Thread.currentThread().getName()+" por el SUR");
            this.cruzar.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void pasar(){
        try {
            this.cruzar.acquire();
                if(!flag){//si no ingreso otro antes que al azar elija cual arranca
                    Random random=new Random();
                    int i= random.nextInt(2);

                    //System.out.println(i);
                    if(i==0){
                        
                       //System.out.println(flag);
                       this.lado_Nor.release(capacidad);  
                                    
                    }else if(i==1){
                       //System.out.println("i");   
                       this.lado_Sur.release(capacidad);    
                    }
                     flag=true;//para que ningun otro hilo acceda
                }

            this.cruzar.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
