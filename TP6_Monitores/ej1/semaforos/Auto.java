package TP6_Monitores.ej1.semaforos;

import java.util.Random;

public class Auto implements Runnable{
    private Puente puente;//objeto compartido
    private char lado;// 's' si es sur , 'n' si es norte
    public Auto(Puente puente,char lado ){
         this.lado=lado;
         this.puente=puente;
    }
    public void run(){
        Random r=new Random();
        try {     

             this.puente.pasar();                
             switch (lado) {
                case 'S':
                    this.puente.cruzarPuenteSur();
                    Thread.sleep(r.nextInt(1000));
                    this.puente.salirPuenteNorte();
                break;
                case 'N':
                    this.puente.cruzarPuenteNor();
                    Thread.sleep(r.nextInt(2000));
                    this.puente.salirPuenteSur();
                break;
                default:

                break;
             }
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
