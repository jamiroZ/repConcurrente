package TP4_SemaforosBinarios.ej7;

import java.util.Random;

public class Empleado implements Runnable {
    private Confiteria espacio;
    private int legajo;
    public Empleado(Confiteria espacio,int legajo){
           this.espacio=espacio;
           this.legajo=legajo;
    }
    public void run(){
         try {
            while( !this.espacio.tomarAsiento()){//si no tomo asiento labura
               palaTime();
            }
            this.espacio.ordenar();
   
            this.espacio.dejarAsiento();
         } catch (Exception e) {
                       // TODO: handle exception
         }
   }                                
    public void palaTime(){
        Random r=new Random();
        try {
              System.out.println(Thread.currentThread().getName()+"labura");
              Thread.sleep(r.nextInt(700));
        } catch (Exception e) {
                  // TODO: handle exception
        }
    }
}
