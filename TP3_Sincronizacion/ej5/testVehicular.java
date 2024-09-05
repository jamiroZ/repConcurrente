package TP3_Sincronizacion.ej5;

public class testVehicular {
    public static void main(String[]args){
        Surtidor surtidor=new Surtidor();//OBJETO COMPARTIDO
        Auto[]autos=new Auto[5];//INTERFAZ
        Thread[]hilos=new Thread[autos.length];//HILOS
        for(int i=0; i< autos.length ;i++){
            autos[i]=new Auto("A"+i, surtidor, "audi"+i, "fiat",100);
        }
        for(int j=0; j< hilos.length ; j++){
            hilos[j]=new Thread(autos[j], autos[j].getPatente());
        }


        //EJECUCION DE LOS HILOS
        for(int i=0; i< hilos.length; i++){
             hilos[i].start();
        }
         try{
            for(int i=0; i< hilos.length; i++){
                hilos[i].join();
            }
        }catch(InterruptedException e){
                e.printStackTrace();
        }
       
    }
}
