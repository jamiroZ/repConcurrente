package TP4_SemaforosBinarios.ej3;

public class Main {
     public static void main(String[] args) {
        Compartido obj=new Compartido();
        Thread h1,h2,h3;
        P1 p1=new P1(obj);
        P2 p2=new P2(obj);
        P3 p3=new P3(obj);
        h1=new Thread(p1);
        h2=new Thread(p2);
        h3=new Thread(p3);
        h1.start();
        h2.start();
        h3.start();
        try {
           h1.join();
           h2.join();
           h3.join(); 
        } catch (Exception e) {
            // TODO: handle exception
        }
        

     }
}
