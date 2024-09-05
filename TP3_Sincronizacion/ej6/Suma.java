package TP3_Sincronizacion.ej6;

public class Suma{//OBJETO COMPARTIDO
      private int total;
      public Suma(){

      }
      public synchronized void sumar(int parcial){
            total=total+parcial;
      }
      public int getTotal(){
          return total;
      }
}