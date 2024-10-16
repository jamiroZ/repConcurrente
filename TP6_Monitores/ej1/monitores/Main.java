package TP6_Monitores.ej1.monitores;

public class Main {
    public static void main(String[] args) {
        Auto []a=new Auto[30];
        GestionaTrafico puente=new GestionaTrafico(10);
        for (int i = 0; i < a.length; i++) {
            if(i % 2 ==0){
                a[i]=new Auto(puente, 'S');
            }else{
                a[i]=new Auto(puente, 'D');
            }   
        }
        Thread[]autos=new Thread[a.length];
        for (int i = 0; i < autos.length; i++) {
            autos[i]=new Thread(a[i],"A"+i);
        }
        for (int i = 0; i < autos.length; i++) {
            autos[i].start();
        }
    }
}
