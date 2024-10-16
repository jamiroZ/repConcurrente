package TP6_Monitores.ej4;

public class Main {
    public static void main(String[] args) {
        Almacen almacen=new Almacen(10);
        Productor p=new Productor(almacen);
        Consumidor c=new Consumidor(almacen);
        Thread consumidor, productor;
        consumidor=new  Thread(c);
        productor=new Thread(p);
        consumidor.start();
        productor.start();
    }
}
