import java.util.Random;

public class Persona implements Runnable {
    private GestorPscina gest;
    private String nombre="juan";
    public Persona(GestorPscina gest){
          this.gest=gest;
          
    }
    public void run(){
          Random r=new Random();
          try {
            gest.ingresarPiscina();
            Thread.sleep(r.nextInt(500));
          } catch (Exception e) {
            // TODO: handle exception
          }
          
          gest.salirPiscina();
    }
    public String getNom(){
        return nombre;
    }
}
