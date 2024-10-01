package TP5_SemaforosGenericos.ej2;

import java.util.Random;

public class Empleado implements Runnable{
    private char opcion;
    private Cafeteria espacio;
     public Empleado(char opcion , Cafeteria espacio){
           this.opcion=opcion;
           this.espacio=espacio;
     }
     public void run(){
        try {
            while( !this.espacio.tomarAsiento()){//si no tomo asiento labura
               palaTime();
            }
            switch(opcion){//realiza una orden
                case 'a'://tomar algo
                       this.espacio.tomarAlgo(); 
                break;
                case 'c'://comer algo  
                       this.espacio.comerAlgo();  
                break;
                case 'b'://tomar y comer algo  
                      this.espacio.tomarAlgo();
                      Thread.sleep(1000);//simula tiempo en tomar,comer o las 2
           
                      this.espacio.comerAlgo();  

                break;
            }
            Thread.sleep(1000);//simula tiempo en tomar,comer o las 2
           
         } catch (Exception e) {
                       // TODO: handle exception
         }
          espacio.dejarAsiento();   
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
