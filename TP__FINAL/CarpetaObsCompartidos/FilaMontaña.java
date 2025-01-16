package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.Semaphore;
public class FilaMontaña {
    private int CAPACIDAD=5;//capacidad de la montaña rusa
    private int ESPACIO=10;//capacidad de espera 
    private int cont;
    private Semaphore asientos;
    private Semaphore espera;
    public FilaMontaña(){
        this.asientos = new Semaphore(this.CAPACIDAD);
        this.espera = new Semaphore(this.ESPACIO);
    }
    public void subirMontaña() throws InterruptedException {

    }
    public void bajarMontaña() throws InterruptedException {

    }

}
