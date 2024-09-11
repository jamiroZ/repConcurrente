package TP4_SemaforosBinarios.ej4;

public class Cliente implements Runnable{
    private String doc;
    private Impresora[] centro;//representa el centro de impresion con k impresoras
    public Cliente(String doc, Impresora []centro){
        this.doc=doc;
        this.centro=centro;
    }
    public void run(){
          int i=0;
          while(i< centro.length){//hasta que no se usen todas las impresoras que repita
                try {
                    centro[i].usar();
                    usando();
                    centro[i].dejar();
                } catch (Exception e) {
                    // TODO: handle exception
                } 
                i++;
                    
          }
    }
    public static void usando(){
          try {
             Thread.sleep(500);
          } catch (Exception e) {
            // TODO: handle exception
          }
    }
}
