package TP6_Monitores.ej7;
import java.util.Random;
public class AutoMoviles implements Runnable {
    private Ferry ferry;
    public AutoMoviles(Ferry ferry){
        this.ferry=ferry;
    }
    public void run(){
 
        try {
           this.ferry.embarqueAutomoviles();
           this.ferry.desembarqueAutomoviles();
        } catch (Exception e) {
           // TODO: handle exception
        }
    }
}
