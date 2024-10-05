package TP5_SemaforosGenericos.ej2;

import java.util.Random;

public class Cocinero implements Runnable{
    private Cafeteria espacio;
    public Cocinero(Cafeteria espacio){
         this.espacio=espacio;
    }
    public void run(){
        Random r=new Random();
        boolean flag=false;
        try {
            while(!flag){//siempre esta en la cafeteria 
                espacio.servirEmpleado();//un empleado quiere comer algo entonces lo atiende
                Thread.sleep(r.nextInt(1000));
                this.espacio.servirComida();//sirve la comida y se libera para otros empleados
               
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
