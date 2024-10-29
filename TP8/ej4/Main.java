package TP8.ej4;

public class Main {
    public static void main(String[] args) {
        SalaDeEspera sala=new SalaDeEspera();
        Persona[]p=new Persona[24];
        for (int i = 0; i < 24; i++) {
            p[i]=new Persona(sala);
        }
        Thread[]personThreads=new Thread[p.length];
        //
        for (int i = 0; i < p.length; i++) {
            personThreads[i]=new Thread(p[i]," persona-"+i);
        }

        //
        for (int i = 0; i < p.length; i++) {
            personThreads[i].start();
        }

    }
}
