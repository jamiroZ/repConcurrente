package TP3_Sincronizacion.ej4;

public class parqueMain {
    public static void main(String[] args) {
        Parque park=new Parque(4);

        Visitante[] hilos = new Visitante[10];//arreglo de visitantes
        //cargo con los visitantes
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Visitante(i, park);
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }

        try {
            for (int i = 0; i < hilos.length; i++) {
                hilos[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
