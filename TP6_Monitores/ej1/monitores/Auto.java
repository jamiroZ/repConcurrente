package TP6_Monitores.ej1.monitores;
import java.util.Random;
public class Auto implements Runnable{
    private GestionaTrafico puente;
    private char lado;
    public Auto(GestionaTrafico puente, char lado){
        this.puente=puente;
        this.lado=lado;
    }
    public void run(){
        
        try {
            Random r=new Random();
            switch (lado) {
                case 'S':
                    this.puente.EntrarCocheDelSur();

                    Thread.sleep( r.nextInt(3000));
                    this.puente.SalirCocheDelSur();
                break;
                case 'D':
                    this.puente.EntrarCocheDelNorte();
                    Thread.sleep( r.nextInt(3000));
                    this.puente.SalirCocheDelNorte();
                break;
            default:
                break;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
