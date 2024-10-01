

public class Main {
    public static void main(String[] args) { 
        GestorPscina gest=new GestorPscina();//objeto compartido
        //
        Thread []per= new Thread[10];
        Persona []p=new Persona[10];
        for (int index = 0; index < p.length; index++) {
              p[index]=new Persona(gest);
        }
        for (int index = 0; index < per.length; index++) {
              per[index]=new Thread (p[index], "A"+index);
        }
        for (int index = 0; index < per.length; index++) {
              per[index].start();
        } 
        try {
            for (int index = 0; index < per.length; index++) {
                per[index].join();
            } 
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
    

