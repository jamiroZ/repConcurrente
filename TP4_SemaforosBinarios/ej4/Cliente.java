package TP4_SemaforosBinarios.ej4;

import java.util.Random;

public class Cliente implements Runnable{
    private String doc;
    private Impresora[] centro;//representa el centro de impresion con k impresoras
    public Cliente(String doc, Impresora []centro){
        this.doc=doc;
        this.centro=centro;
    }
    public void run(){
          Random r=new Random();
          int i=0;
          Boolean flag=false;
          try {
                while(!flag){//entra al centro de impresion y busca impresora
                    i=r.nextInt(4);
                    //System.out.println(flag);
                    if(centro[i].usar()){//si encontro impresora la usa
                        flag=true;//
                        usando();
                        centro[i].dejar();//termino libera la impresora
                    }
                }    
            } catch (Exception e) {
                    // TODO: handle exception
           }
                
          
    }
    public static void usando(){
         Random r=new Random();
          try {
             Thread.sleep(r.nextInt(10000));
          } catch (Exception e) {
            // TODO: handle exception
          }
    }
}
