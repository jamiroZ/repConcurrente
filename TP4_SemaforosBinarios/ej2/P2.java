package TP4_SemaforosBinarios.ej2;
public class P2 implements Runnable{
    private Compartido obj;
    public P2(Compartido obj){
        this.obj=obj;
    }
    public void run(){
         try{
              obj.adquirirS3();
              Thread.sleep(500);
         }catch(InterruptedException e){
 
         }
         obj.liberarS2();
    }
}
