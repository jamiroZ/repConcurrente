package TP3_Sincronizacion.ej6;

public class SumaParcial implements Runnable{
    private Suma suma;
     private int inf;
     private int sup;
     private int[]array;
     public SumaParcial(int inf, int sup,int[]array,Suma suma){
         this.inf=inf;
         this.sup=sup;
         this.array=array;
         this.suma=suma;
     }
     public void run(){
         try{
            int parcial=0;
            while(inf <= sup){
               parcial=parcial+array[inf];
               inf++;
            }
            System.out.println(parcial);
            suma.sumar(parcial);
         }catch(Exception e){
         
         }
     }
}
