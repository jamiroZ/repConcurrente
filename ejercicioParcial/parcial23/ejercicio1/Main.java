package ejercicioParcial.parcial23.ejercicio1;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Random r=new Random();
        Fabrica embotelladora=new Fabrica();
        Transportador  transportador=new Transportador(embotelladora);
        Thread hiloTransportador =new Thread(transportador);
        
        Embotellador[]emb=new Embotellador[1];
        Empaquetador[]emp=new Empaquetador[1];

        for (int i = 0; i < emp.length+ emp.length; i++) {
            if(i <emb.length){
                int  aleatorio=r.nextInt(2);
                if(aleatorio==0){
                    emb[i]=new Embotellador(embotelladora,'a');
                }else{
                    emb[i]=new Embotellador(embotelladora,'v');
                }

                
            }
            if(i<emp.length){
                emp[i]=new Empaquetador(embotelladora);
            }
        }
        Thread []embotelladores=new Thread[emb.length];
        Thread []empaquetadores=new Thread[emp.length];
        for (int i = 0; i < emp.length+emb.length; i++) {
               if(i<emb.length){
                  embotelladores[i]=new Thread(emb[i],"embotellador-"+i);
               }
               if(i<emp.length){
                  empaquetadores[i]=new Thread(emp[i],"empaquetador-"+i);
               }
        }
        for (int i = 0; i < emp.length+emb.length; i++) {
            if(i<emb.length){
               embotelladores[i].start();
            }
            if(i<emp.length){
               empaquetadores[i].start();
            }
        }
        hiloTransportador.start();
    }
}
