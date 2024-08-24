package TP2_Hilos.ej2;

public class MiEjecucion extends Thread{
    public void run(){
        try{ 
            ir();
            Thread.sleep(20000);//le doy tiempo al hilo para que muestre el cartel "en la pila" antes del main
        }catch(InterruptedException e){

        }
    }
    public void ir(){
        hacerMas();
    }
    public void hacerMas(){
       System.out.println("En la pila");
    }
}
