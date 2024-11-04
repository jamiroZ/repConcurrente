package ejercicioParcial.parcial22.ejercicio1;
import java.util.Random;
public class MainParque {
    public static void main(String[] args) {
        Parque parque=new Parque(7);
        Persona []p=new Persona[25];
        Thread[]personas=new Thread[p.length];
        Random r=new Random();
        //cargo datos en Persona y los HilosPersona
        for (int i = 0; i < p.length; i++) {
            int  aleatorio=r.nextInt(3);
            if  (aleatorio==0) {
                p[i]=new Persona(parque,true );
                personas[i]=new Thread(p[i]," vecino-"+i);
            }else{
                p[i]=new Persona(parque, false);
                personas[i]=new Thread(p[i]," persona-"+i);
            }
        }
         //hilos listos para  ejecutarse

        for (int i = 0; i < personas.length; i++) {
            personas[i].start();
        }
    }
}
