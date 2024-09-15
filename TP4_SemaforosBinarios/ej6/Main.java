package TP4_SemaforosBinarios.ej6;

public class Main {
    public static void main(String []args) {
         Taxi comp=new Taxi("AA42");//OBJETO COMPARTIDO
         //HILOS
         Taxista tax=new Taxista(comp);
         Pasajero []pas=new Pasajero[10];
         //
         for(int i=0; i< pas.length; i++){
             pas[i]=new Pasajero("ale"+i, comp);
         }
         Thread taxista=new Thread(tax);
         Thread [] pasajeros=new Thread[pas.length];
         for(int i=0; i< pasajeros.length; i++){
                pasajeros[i]=new Thread(pas[i]);
         }
         //HILOS LISTOS
         taxista.start();
         for(int i=0; i< pasajeros.length; i++){
             pasajeros[i].start();
         }
         
         //
         try {
            taxista.join();
            for(int i=0; i< pasajeros.length; i++){
               pasajeros[i].join();
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
         
    }
}
