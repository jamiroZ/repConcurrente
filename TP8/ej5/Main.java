package TP8.ej5;

public class Main{
    public static void main(String[] args) {
        Hoguera h=new Hoguera(5);
        Cocinero co=new Cocinero(h);
        Thread cocinero=new Thread(co, " cocinero ");

        Canibales[]ca=new Canibales[20];
        Thread[]caniThreads=new Thread[ca.length];
       
        cocinero.start();
        for(int i=0;i< ca.length;i++){
            ca[i]=new Canibales(h);
            caniThreads[i]=new  Thread(ca[i], " canibal " + i);
            caniThreads[i].start();
        }

    }
}