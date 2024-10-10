package TP5_SemaforosGenericos.ej3;

import java.util.Random;

public class Perro implements Runnable{
    private Comedero objComedor;
    public Perro(Comedero objComedor){
         this.objComedor=objComedor;
    }
    public void run(){
        Random r=new Random();
        try {
            Thread.sleep( r.nextInt(200));
            this.objComedor.comerPerro();
            Thread.sleep(r.nextInt(1000));
            System.out.println("come");
            this.objComedor.salirPerro();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
