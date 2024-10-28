package TP7_Locks.ej3;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r=new Random();
        String tipos="cvp";
        Cocina cocina=new Cocina(4, 7, 5);
        Cocinero[]c=new Cocinero[20];
        for (int i = 0; i < 15; i++) {
            char aux= tipos.charAt(r.nextInt(3));
            c[i]=new Cocinero(cocina, aux);
        }
        Thread []cocineros=new Thread[c.length];
        for (int i = 0; i < cocineros.length; i++) {
             cocineros[i]=new  Thread(c[i],  "Cocinero "+i);

             cocineros[i].start();

        }

    }
}
