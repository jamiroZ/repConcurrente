package TP3_Sincronizacion.ej2;

public class MainTest {
    public static void main(String []args){
        Energia energia=new Energia();
        CriaturaOscura hilo1=new CriaturaOscura(energia);
        Sanador hilo2=new Sanador(energia);
        System.out.println("en main ANTES DE ESTAR LISTOS LOS HILOS: "+ energia.getEnergia());
        hilo1.start();
        hilo2.start();
        try{
            hilo1.join();
            hilo2.join();
        }catch(Exception e){

        }
        System.out.println("en main DESPUES: "+ energia.getEnergia());

    }
}
