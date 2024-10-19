package TP6_Monitores.ej5;

public class Main {
    public static void main(String[] args) {
        Almacen almacen=new Almacen(5);
        Productor p=new Productor(almacen);
        Consumidor [] c=new Consumidor[10];
        Thread  productor;
        productor=new Thread(p);
        
        for(int i=1; i< c.length ; i++){
            c[i]=new Consumidor(almacen);
        } 
        Thread []consumidor=new Thread[c.length];
        for(int i=1 ; i <consumidor.length ;i++){
            consumidor[i]=new Thread(c[i], "C"+i);
        }
        productor.start();
        for(int i=1; i< c.length; i++){
            consumidor[i].start();
        }   
        
    }
}