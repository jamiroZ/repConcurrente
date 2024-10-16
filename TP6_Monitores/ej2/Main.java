package TP6_Monitores.ej2;

public class Main {
    public static void main(String[] args) {
        Alumno[]a=new Alumno[13];
        SalaDeEstudio sala=new SalaDeEstudio(5);
        for (int i = 0; i < a.length; i++) {
            a[i]=new Alumno(sala);
        }
        Thread [] alumnos= new Thread[a.length];
        for (int i = 0; i < a.length; i++) {
                alumnos[i]=new  Thread(a[i] , "Alumno-"+i);

        }
        for (int i = 0; i < a.length; i++) {
            alumnos[i].start();

        }
    }
}
