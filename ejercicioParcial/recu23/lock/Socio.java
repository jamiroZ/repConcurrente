package ejercicioParcial.recu23.lock;
import java.util.Random;
public class Socio implements Runnable {
    private Serie capitulos;
    private boolean doblaje;
    public Socio(Serie capitulos,  boolean doblaje) {
        this.doblaje=doblaje;
        this.capitulos=capitulos;
    }
    public void run(){
        Random r=new Random();
        try {
            while(true){
               if(this.doblaje){//quiere ver el episodio doblada al ingles
                   this.capitulos.verEnIngles();
               }else{
                   this.capitulos.verEnEspañol();
               }
            
               Thread.sleep(r.nextInt(3000));//simula tiempo viendo el cap
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
