package TP4_SemaforosBinarios.ej4;

import java.util.concurrent.Semaphore;

public class Impresora {
    private Semaphore disponible;
    private int numI;
    public Impresora(int numI){
           this.numI=numI;
           this.disponible=new Semaphore(1);
    }
    public void usar(){
        try {
            this.disponible.acquire();
            System.out.println(Thread.currentThread().getName()+" usa impresora "+numI);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void dejar(){
        System.out.println(Thread.currentThread().getName()+" dejo de usar impresora "+numI);
        this.disponible.release();//libera impresora
    }
}
