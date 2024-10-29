package ejercicioParcial.parcial23.ejercicio1;

public class Empaquetador implements Runnable{
    private Fabrica embotelladora;
    
    public Empaquetador(Fabrica embotelladora){
         this.embotelladora=embotelladora;
    }
    public void run(){
        try {
        while(true){
            this.embotelladora.empaquetarCaja();
        }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
