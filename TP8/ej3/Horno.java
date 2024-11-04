package TP8.ej3;
import java.util.Random;
public class Horno implements Runnable {
    private Pasteleria pasteles;
    private int canPasteles;//cantidad de pasteles pára hornear
    private int pesoPastel;//peso de cada pastel
    //peso en gramos de 3 tipos
    private int pesoA=400;
    private int pesoB=600;
    private int pesoC=500;
    public Horno(Pasteleria pasteles,int canPasteles,int pesoPastel){
        this.pasteles = pasteles;//
        this.canPasteles=canPasteles;//
        this.pesoPastel=pesoPastel;//
    }
    public void run(){
        Random r=new Random();
         try {
            int cont=0;
            //que cocine n pasteles ,cada horno
            while(cont < this.canPasteles){
                   
                 Thread.sleep(r.nextInt(3000)+1000);//tiempo cocinando pastel
                 this.pasteles.ponerEnMostradorPastel(pesoPastel);//pone en mostradorPastel
                 cont++;
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
