package TP7_Locks.ej3;
import java.util.Random;
public class Cocinero implements Runnable{
    private Cocina cocina;
    private char receta;//ya tiene una de 3
    public Cocinero(Cocina cocina, char receta){
        this.cocina = cocina;
        this.receta=receta;
    }
    public void run(){
        Random r=new Random();
        try {
            //segun el tipo de receta que ya tenga
            //intenta agarrar los 2 ingredientes que le falte
            switch (receta) {
                case 'v':
                    this.cocina.cocinarCarne();
                    this.cocina.cocinarPasta();
                    Thread.sleep(r.nextInt(5000));
                    this.cocina.reponerCarne();
                    this.cocina.reponerPasta();
                break;
                case 'c':
                    this.cocina.cocinarPasta();
                    this.cocina.cocinarVerdura();
                    Thread.sleep(r.nextInt(5000));
                    this.cocina.reponerPasta();
                    this.cocina.reponerVerdura();
                break;
                case 'p':
                    this.cocina.cocinarVerdura();
                    this.cocina.cocinarCarne();
                    Thread.sleep(r.nextInt(5000));
                    this.cocina.reponerCarne();
                    this.cocina.reponerVerdura();
                             
                break;
                default:
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

