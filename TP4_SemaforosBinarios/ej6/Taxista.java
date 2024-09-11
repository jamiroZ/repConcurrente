package TP4_SemaforosBinarios.ej6;

import java.util.Random;

import org.omg.PortableServer.THREAD_POLICY_ID;

public class Taxista implements Runnable{
    private Taxi taxi;
    public Taxista(Taxi taxi){
        this.taxi=taxi;
    }
    public void run(){
        
    }
    public static void dormir(){
        Random r=new Random();
        try {
            Thread.sleep(r.nextInt(3)+50);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
