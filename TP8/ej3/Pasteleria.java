package TP8.ej3;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.Semaphore;

public class Pasteleria {
     //los pasteles pueden ser de estos 3 pesos(en gramos)
     private int pesoA;
     private int pesoB;
     private int pesoC;
     //pueden hacerse 3 pasteles porque hay 3 hornos
     private Semaphore pastel_Horno=new Semaphore(3);//
     //peso maximo de las cajas
     private int pesoMaximoCaja;//maximo de peso para llenar cajas
     private int caja=0;//simula la caja vacia para pasteles
     Lock lock=new ReentrantLock();
     public Pasteleria( int pesoA, int pesoB, int pesoC, int pesoMaximoCaja){
            this.pesoMaximoCaja=pesoMaximoCaja;
            
            this.pesoA=pesoA;
            this.pesoB=pesoB;
            this.pesoC=pesoC;

     }
     public void cocinarPastel(){
        try {
            this.pastel_Horno.acquire();

        } catch (Exception e) {
            // TODO: handle exception
        }
     }
     public void ponerEnMostradorPastel(int  pesoPastel){
        
        System.out.println(Thread.currentThread().getName()+" pone en  el mostrador un pastel de "+this.pesoA+" gramos");

        this.pastel_Horno.release();
     }
     public void retirarCaja(){

     }
     public void reponerCaja(){
        
     }
     public void tomarPastel(){

     }
     public void soltarPastel(){
        
     }

}
