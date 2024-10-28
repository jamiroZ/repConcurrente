package TP8.ej1;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Random r=new Random();
        Comedero comedero=new Comedero(100);
        Soldado [] s=new Soldado[200];
        for (int i = 0; i < s.length; i++) {
            int gaseosa= r.nextInt(3);
            int postre= r.nextInt(7);
            
            s[i]=new Soldado(comedero, gaseosa,postre);
        
        }
        Thread  [] soldado=new Thread[s.length];
        for (int j = 0; j < soldado.length; j++) {
            soldado[j]=new Thread(s[j], " soldado-"+j);
        }
        for (int j = 0; j < soldado.length; j++) {
            soldado[j].start();
        }
    
    }
}
