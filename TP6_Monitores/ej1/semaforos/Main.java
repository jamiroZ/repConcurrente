package TP6_Monitores.ej1.semaforos;

public class Main {
    public static void main(String[] args) {
        Puente puente=new Puente(5);
        Auto[]a=new Auto[14];
        for (int i = 0; i < a.length; i++) {
            if(i%2==0){
                a[i]=new Auto(puente,'S');
            }else{
                a[i]=new Auto(puente,'N');
            }         
        }
        Thread[]autos=new Thread[a.length];
        for (int i = 0; i < autos.length; i++) {
            autos[i]=new Thread(a[i],"A"+i);
        }
        for (int i = 0; i < autos.length; i++) {
            autos[i].start();
        }

    }
}
