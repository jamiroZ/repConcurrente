package TP3_Sincronizacion.ej3;

public class Plato {
    public Plato(){

    }
    public synchronized void comer(){
        System.out.println(Thread.currentThread().getName() +"come");
        try{//simulo el tiempo
            Thread.sleep(1000);
        }catch(Exception e){

        }
        System.out.println(Thread.currentThread().getName() +"dejo de comer");
    }
}
