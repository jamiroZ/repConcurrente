package TP6_Monitores.ej1.semaforos;

public class Auto implements Runnable{
    private Puente puente;//objeto compartido
    private char lado;// 's' si es sur , 'n' si es norte
    public Auto(Puente puente,char lado ){
         this.lado=lado;
         this.puente=puente;
    }
    public void run(){

    }
}
