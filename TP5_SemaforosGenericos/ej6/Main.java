package TP5_SemaforosGenericos.ej6;

public class Main {
    public static void main(String[] args) {
        TorreDeControl torre=new TorreDeControl(5);
        Avion[]aviones=new Avion[5];
        Thread[]av=new Thread[aviones.length];
        for(int i=1 ; i< aviones.length; i++){
            aviones[i]=new Avion(torre);
        }
        for(int i=1; i< av.length ;i++){
            av[i]=new Thread ( aviones[i], "a"+i);
        }
        for(int i=1; i< av.length ;i++){
            av[i].start();
        }
    }
}
