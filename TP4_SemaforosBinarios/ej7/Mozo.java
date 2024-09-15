package TP4_SemaforosBinarios.ej7;

import java.util.Random;

public class Mozo implements Runnable {
    private Confiteria espacio;
    public Mozo(Confiteria espacio){
         this.espacio=espacio;
    }
    public void run(){
        Random r=new Random();
        boolean flag=false;
        try {
            while(!flag){//siempre esta en la cafeteria 
                if(espacio.atenderEmpleado()){//hay empleado sentado
                     Thread.sleep(r.nextInt(700));
                     this.espacio.servirComida();
                 }else{//si no hay nadie
                     Thread.sleep(1300);//simula tiempo de inventar reseta
                 }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
