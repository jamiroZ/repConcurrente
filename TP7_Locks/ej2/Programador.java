package TP7_Locks.ej2;

import java.util.Random;

public class Programador implements Runnable {
    private ProyectoSoft proy;
    public Programador(ProyectoSoft proy){
        this.proy = proy;
    }
    public void run(){
        Random r=new Random();
        try {

            this.proy.trabajarProyecto();
            Thread.sleep(r.nextInt(4000)+1000);
            this.proy.dejarProyecto();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
