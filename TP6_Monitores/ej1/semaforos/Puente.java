package TP6_Monitores.ej1.semaforos;

import java.util.concurrent.Semaphore;

import TP2_Hilos.ej3y4.ThreadEjemplo;

public class Puente {
    private Semaphore cruzar;//exclusion
    private Semaphore capacidad;//cant autos que pueden pasar al mismo tiempo
    
    
    //contador de cuantos autos cruzaron de cada lado
    private int cantcruzadosSur;
    private int cantcruzadosNor;
    //cont AUtos en el puente
    private int cantAutosSur;
    private int cantAutosNor;
    public Puente(int cant){
           this.capacidad=new Semaphore(cant);
           this.cruzar=new Semaphore(1);
           this.cantAutosNor=0;
           this.cantAutosSur=0;
           //
           this.cantcruzadosSur=0;
           this.cantcruzadosNor=0;
    }
    public void cruzarPuenteDer(){
         try {
             this.cruzar.acquire();
               System.out.println("cruza el pente el auto "+Thread.currentThread().getName()+" ");
             
            this.cruzar.release();
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
    public void cruzarPuenteIzq(){
        try {
            this.cruzar.acquire();
                System.out.println("cruza el pente el auto "+Thread.currentThread().getName()+" ");
       
          
                this.cruzar.release();
        } catch (Exception e) {
           // TODO: handle exception
        }
    }
    public void salirPuenteIzq(){
        
    }
    public void salirPuenteDer(){
        
    }

}
