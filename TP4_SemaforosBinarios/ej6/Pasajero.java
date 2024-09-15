package TP4_SemaforosBinarios.ej6;

import java.util.Random;

public class Pasajero implements Runnable{
    private String name;
    private Taxi taxi;
    public Pasajero(String name, Taxi taxi){
            this.name=name;
            this.taxi=taxi;
    }
    public void run(){
        boolean condicion=false;//camina 
        try {
           while(!condicion){//que camine mientras no encuentre taxi
                if(this.taxi.subirTaxi()){
                    condicion=true;
                }else{
                    caminar();
                }
           }
        } catch (Exception e) {
            // TODO: handle exception
        }   
         
         
    }
    public static void caminar(){
        Random r=new Random();
        try {
            Thread.sleep(r.nextInt(700)+100);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
