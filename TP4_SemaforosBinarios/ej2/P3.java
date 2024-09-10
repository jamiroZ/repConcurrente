package TP4_SemaforosBinarios.ej2;
public class P3 implements Runnable {
    private Compartido obj;
    public P3(Compartido obj){
        this.obj=obj;
    }
    public void run(){
         try{
             obj.adquirirS1();
              Thread.sleep(500);
         }catch(InterruptedException e){

         }
         obj.liberarS3();
         obj.liberarS4();
    }
}
