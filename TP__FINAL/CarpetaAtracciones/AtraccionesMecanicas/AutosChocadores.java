package TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas;

import java.util.Random;

public class AutosChocadores implements Runnable {


    
    public AutosChocadores(){

    }
    public void run(){
        Random r=new Random();
        try {
 
            Thread.sleep(r.nextInt(2000)+1000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
