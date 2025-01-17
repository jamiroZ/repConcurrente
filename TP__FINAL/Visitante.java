package TP__FINAL;
import java.util.Random;
import TP__FINAL.CarpetaObsCompartidos.FilaMontaña;

public class Visitante implements Runnable{
    private FilaMontaña fila;
    public Visitante(FilaMontaña fila){
        this.fila = fila;
    }
    public void run(){
        Random r=new Random();
        Boolean log=false;
        try {
               log=this.fila.subirMontaña();
               if(log){
                 this.fila.bajarMontaña();
               }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}