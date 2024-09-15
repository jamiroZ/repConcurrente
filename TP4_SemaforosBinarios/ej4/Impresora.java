package TP4_SemaforosBinarios.ej4;

import java.util.concurrent.Semaphore;

public class Impresora {
    private Semaphore disponible;
    private int numI;
    public Impresora(int numI){
           this.numI=numI;
           this.disponible=new Semaphore(1);
    }
    public boolean usar(){
        boolean usando=false;//si no pudo usar la impresora retorna
        try {
            //System.out.println(this.disponible.tryAcquire());
            if(this.disponible.tryAcquire()){//INTENTA USAR LA IMPRESORA
               usando=true;
               System.out.println(Thread.currentThread().getName()+" usa impresora "+numI);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return usando;
    }
    public void dejar(){
        
        System.out.println(Thread.currentThread().getName()+" dejo de usar impresora "+numI);
        this.disponible.release();//libera impresora
    }
}
