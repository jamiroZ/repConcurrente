package TP3_Sincronizacion.ej3;

public class Hamaca {
    public Hamaca(){

    }
    public synchronized void duerme(){
        System.out.println(Thread.currentThread().getName() +"duerme");
        try{//simulo el tiempo
            Thread.sleep(1000);
       }catch(Exception e){

       }
       System.out.println(Thread.currentThread().getName() +"desperto");
    }
}
