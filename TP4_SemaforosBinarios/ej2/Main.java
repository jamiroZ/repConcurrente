package TP4_SemaforosBinarios.ej2;

public class Main {
    public static void main(String[] args) {
         Compartido obj=new Compartido(0,1,0,0);
         Thread p1,p2,p3,p4;
         P1 proceso1=new P1( obj );
         P2 proceso2=new P2( obj );
         P3 proceso3=new P3( obj );
         P4 proceso4= new P4( obj );
         //
         p1=new Thread(proceso1);
         p2=new Thread(proceso1);
         p3=new Thread(proceso1);
         p4=new Thread(proceso1);
         //HILOS LISTOS 
         p1.start();
         p2.start();
         p3.start();
         p4.start();
         //
         try {
            p1.join();
            p2.join();
            p3.join();
            p4.join();
         } catch (Exception e) {
            // TODO: handle exception
         }
         
    } 
}
