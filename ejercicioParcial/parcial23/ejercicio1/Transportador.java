package ejercicioParcial.parcial23.ejercicio1;

public class Transportador implements Runnable {
    private Fabrica embotelladora;
    public Transportador(Fabrica embotelladora){
          this.embotelladora=embotelladora;
    }
    public void run(){
        try {
            this.embotelladora.vaciarAlmacen();//el camion arranca
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
