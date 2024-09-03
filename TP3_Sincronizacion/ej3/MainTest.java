package TP3_Sincronizacion.ej3;

public class MainTest {
    public static void main(String[] args) {
        Jaula jaula=new Jaula(new Hamaca(), new Plato(), new Rueda());
        Hamster[]hilos=new Hamster[10];
        for(int i=0; i< hilos.length; i++){
            hilos[i]=new Hamster(jaula);
        }
        for(int i=0; i< hilos.length; i++){
            hilos[i].start();
        }
        for(int i=0; i< hilos.length; i++){
            try{
                 hilos[i].join();
            }catch(Exception e){
                 System.out.println(e);
            }
          
        }
    }
    
}
