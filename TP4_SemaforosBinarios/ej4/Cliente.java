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
          Boolean flag=false;
          while(!flag){//hasta que no se usen todas las impresoras que repita
                try {
                    flag=centro[i].usar();
                    if(flag){
                        usando();
                        centro[i].dejar();
                        
                    }
                    
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
