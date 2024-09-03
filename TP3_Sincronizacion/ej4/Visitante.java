package TP3_Sincronizacion.ej4;

public class Visitante extends Thread{
     private int num;
     private Parque unParque;//OBJETO COMPARTIDO
     public Visitante(int num,Parque unParque){
         this.num=num;
         this.unParque=unParque;
     }
     public int getVisitante(){
        return this.num;
     }
     public void run(){
        //mando un numero aleatorio
        int areaElegida = (int) ((Math.random() * unParque.getCantAreas()));
        unParque.reservarArea(areaElegida);
     }
}
