package TP3_Sincronizacion.ej6;

public class SumaMain{
    public static void main(String[] args){
         Suma suma=new Suma();
         int[]array=new int[50000];
         //cargo el array con 50.000 enteros entre 1 y 10
         for(int i=0; i< array.length;i++){
               array[i]= (int) (Math.random() * 10) + 1;
         }
    
         int k=10;
         SumaParcial[]sum=new SumaParcial[k];
         Thread[]hilos=new Thread[sum.length];
         int subSumas=50000 / k;//cantidad de sumas que cada hilo va a realizar
         
         for(int i=0; i < k ; i++){
            int inf=i*subSumas;//posicion actual
            int sup=(i+1)*subSumas;//siguiente posicion
            System.out.println(inf+" "+sup);
            sum[i]=new SumaParcial(inf, sup, array,suma);//mando rango(min y max de la suma)y el array
         }
         for(int i=0; i < k ; i++){
               hilos[i]=new Thread(sum[i]);
         }
         for(int i=0; i< k; i++){
            hilos[i].start();
         }
         for(int i=0; i< k; i++){
            try{
                hilos[i].join();
            }catch(InterruptedException e){
            }
            
         }
         System.out.println(suma.getTotal());
    }    
}