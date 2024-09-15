package TP4_SemaforosBinarios.ej6;

import java.util.Random;

public class Taxista implements Runnable{
    private Taxi taxi;
    public Taxista(Taxi taxi){
        this.taxi=taxi;
    }
    public void run(){
        Random r=new Random();
        try {
            while(true){
                this.taxi.arrancar();
                //SIMULA TIEMPO DEL VIAJE
                Thread.sleep(r.nextInt(4000));
                this.taxi.detener();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
