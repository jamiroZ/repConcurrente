package TP5_SemaforosGenericos.ej8;

import java.util.Random;

public class Babuinos implements Runnable{
    private Kruger parqueNacional;
    private char lado;//lado del cañon en el que esta el babuino
    public Babuinos(Kruger parqueNacional,char lado){
         this.parqueNacional=parqueNacional;
         this.lado=lado;
     }
     public void run(){
         Random r=new Random();
         try {
            Thread.sleep(r.nextInt(100));
             switch (lado) {
                case 'I':
                    parqueNacional.cruzarCuerdaIzq();
                    Thread.sleep(r.nextInt(1000));
                    parqueNacional.bajarCuerdaIzq();
                break;
                case 'D':
                    parqueNacional.cruzarCuerdaDer();
                    Thread.sleep(r.nextInt(2000));
                    parqueNacional.bajarCuerdaDer();
                break;
                default:

                break;
             }
         } catch (Exception e) {
            // TODO: handle exception
         }
        
     }
}
