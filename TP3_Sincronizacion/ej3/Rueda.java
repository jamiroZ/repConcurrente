package TP3_Sincronizacion.ej3;

public class Rueda {
    public Rueda(){

    }
    public synchronized void jugar(){
          System.out.println(Thread.currentThread().getName() +"juega");
          try{//simulo el tiempo
               Thread.sleep(1000);
          }catch(Exception e){

          }
          System.out.println(Thread.currentThread().getName() +"dejo de jugar");
    }
}