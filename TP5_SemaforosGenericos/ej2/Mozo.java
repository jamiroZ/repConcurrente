package TP5_SemaforosGenericos.ej2;

import java.util.Random;

public class Mozo implements Runnable {
    private Cafeteria espacio;
    public Mozo(Cafeteria espacio){
         this.espacio=espacio;
    }
    public void run(){
        Random r=new Random();
        boolean flag=false;

        try {
            
            while(!flag){//siempre esta en la cafeteria 
                this. espacio.atenderEmpleado();//hay empleado sentado
                Thread.sleep(r.nextInt(700));
                
                this.espacio.servirBebida();
                
                Thread.sleep(0);//simula tiempo de inventar reseta
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
