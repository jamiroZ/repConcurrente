package TP8.ej1;
import java.util.Random;
public class Soldado implements Runnable {
    private Comedero comedero;
    private int post;
    private int gaseosa;
    public Soldado( Comedero comedero, int gaseosa, int post){
        this.comedero=comedero;
        this.post=post;
        this.gaseosa=gaseosa;
    }
    public void run(){
        Random r=new Random();
       try {
        this.comedero.entrarComedero();
           this.comedero.tomarBandeja();
               if(this.gaseosa==2){//si quiere gaosa que busque para abrirla
                  this.comedero.abrirGaseosa();
               }
               Thread.sleep(r.nextInt(2000)+1000);//tiempo comiendo
           
               if(post==5){//si quiere postre 
                  this.comedero.irMostradorPostre();
                  Thread.sleep(r.nextInt(1000)+1000);//tiempo comiendo
                  this.comedero.salirMostradorPostre();
               }
            this.comedero.dejarBandeja();
        this.comedero.salirComedero();
           
       } catch (Exception e) {
        // TODO: handle exception
       }
    }
}
