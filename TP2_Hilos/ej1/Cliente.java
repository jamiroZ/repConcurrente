package TP2_Hilos.ej1;

public class Cliente extends Thread {
    public void run(){
       System.out.println("soy"+Thread.currentThread().getName());
       
       try {
          Thread.sleep(2000);//duermo el hilo 2000mlsegundos
       }catch (InterruptedException e) {
       };
       Recurso.uso();//muestra
    };
}