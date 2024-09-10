package TP4_SemaforosBinarios.ej2;

public class P4 implements Runnable{
    private Compartido obj;
    public P4(Compartido obj){
        this.obj=obj;
    }
    public void run(){
         try{
             obj.adquirirS4();
              Thread.sleep(500);
         }catch(InterruptedException e){

         }
    }
}
