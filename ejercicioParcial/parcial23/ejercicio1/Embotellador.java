package ejercicioParcial.parcial23.ejercicio1;
import java.util.Random;

public class Embotellador implements Runnable{
    private Fabrica embotelladora;
    private char tipo;//si es embotellador de vino 'v', sino es de agua 'a'
    public Embotellador(Fabrica embotelladora,char tipo){
        this.embotelladora = embotelladora;
        this.tipo=tipo;
    }
    public void run(){
        Random r=new Random();
         try {
            while(true){
                if(this.tipo=='a'){//embotellador de agua
                     this.embotelladora.embotellarAgua();
                }else {//embotellador de vino
                     this.embotelladora.embotellarVino();
                }
                Thread.sleep(r.nextInt(1000)+1000);
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
