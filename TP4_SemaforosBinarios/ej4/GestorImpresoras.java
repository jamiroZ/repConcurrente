package TP4_SemaforosBinarios.ej4;

public class GestorImpresoras {
    public static void main(String[] args) {
        Impresora[]centro=new Impresora[4];
        for(int i=0; i< centro.length ; i++){
            centro[i]=new Impresora(i);
        }
        Cliente clientes[]=new Cliente[10];
        for(int i=0; i< clientes.length ; i++){
                clientes[i]=new Cliente("A42"+i, centro);
        }
        Thread[]hilos=new Thread[clientes.length];
        for(int i=0; i< hilos.length; i++){
           hilos[i]=new Thread(clientes[i]);
        }
        for(int i=0; i< hilos.length; i++){
            hilos[i].start();
         }
         for(int i=0; i< hilos.length; i++){
            try{
                 hilos[i].join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
         }

    }
}
