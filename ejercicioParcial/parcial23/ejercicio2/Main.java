package ejercicioParcial.parcial23.ejercicio2;

public class Main {
    public static void main(String[] args) {
        Espacio espacio=new Espacio();
        Hidrogeno []h=new Hidrogeno[1];
        Oxigeno [] o=new Oxigeno[1];
        Thread [] hidro=new Thread[h.length];
        Thread[] oxi=new Thread[o.length];
        for (int i = 0; i < h.length; i++) {
             h[i]=new Hidrogeno(espacio);
             o[i]=new Oxigeno(espacio);
        }
        for (int i = 0; i < oxi.length; i++) {
                hidro[i]=new Thread(h[i], " hidrogeno"+i);
                oxi[i]=new Thread(o[i],  "oxigeno"+i);

        }
        for (int i = 0; i < oxi.length; i++) {
            hidro[i].start();
            oxi[i].start();
        }
    }

}
