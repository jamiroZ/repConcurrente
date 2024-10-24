package TP6_Monitores.ej7;

import TP2_Hilos.ej3y4.ThreadEjemplo;

public class Main {
    public static void main(String[] args) {
        //objeto compartido
        Ferry ferry=new Ferry(10);
        //hilos
        ChoferFerry chofer=new ChoferFerry(ferry);
        Pasajero[]p=new Pasajero[10];
        //cargo los arreglos de objetos 
        for(int i=0; i< p.length;i++){
               p[i]=new Pasajero(ferry);
        } 
        AutoMoviles[]a=new AutoMoviles[7];
        for(int i=0;i<a.length;i++){
            a[i]=new AutoMoviles(ferry);
        }

        //creo los hilos
        Thread[]pasajeros=new Thread[p.length];//personas
        for(int i=0;i<pasajeros.length;i++){
            pasajeros[i]=new Thread(p[i], "pasajero-"+i);
        }
        Thread[]automoviles=new Thread[a.length];//automoviles
        for(int i=0;i<automoviles.length;i++){
            automoviles[i]=new Thread(a[i], "automovil-"+i);
        }
        //estan listos los hilos y arrancan
        Thread ferryThread=new Thread(chofer);
         ferryThread.start();
        for(int i=0;i<pasajeros.length+automoviles.length;i++){
                 if(i <automoviles.length){//AUTOMOVILES
                    automoviles[i].start();
                 }
                 if(i < pasajeros.length){//PASAJEROS
                    pasajeros[i].start();
                 }
        }
       

    }
}
