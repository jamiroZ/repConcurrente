package TP7_Locks.ej2;

public class Main {
    public static void main(String[] args) {
        Programador[]p=new Programador[10];
        ProyectoSoft proyecto=new ProyectoSoft(6, 6);
        for (int i = 0; i < p.length; i++) {
            p[i]=new Programador(proyecto);
        }
        Thread[] programadores=new Thread[p.length];
        for (int i = 0; i < programadores.length; i++) {
            programadores[i]=new Thread( p[i],"P"+i);
        }
        for (int i = 0; i < programadores.length; i++) {
            programadores[i].start();
        }
    }
}
