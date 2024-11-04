package ejercicioParcial.parcial22.ejercicio1;
import java.util.Random;
public class Persona implements Runnable{
    private boolean preferente;//si es vecino true, sino false
    private Parque parque;
    public Persona(Parque parque, boolean preferente){
         this.parque = parque;
         this.preferente = preferente;
    }
    public void run(){
        Random r=new Random();
        try {
            if(this.preferente){
              this.parque.entradaPreferente();
            }else{
                this.parque.entradaNormal();
            }
            Thread.sleep(r.nextInt(4000)+1000);
            this.parque.salir(this.preferente);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
