package TP4_SemaforosBinarios.ej2;
public class P1 implements Runnable{
    private Compartido obj;
    public P1(Compartido obj){
        this.obj=obj;
    }
    public void run(){
         try{
            obj.adquirirS2();
              Thread.sleep(500);
         }catch(InterruptedException e){
                
         }
         obj.liberarS1();
    }
}
