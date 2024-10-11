package TP5_SemaforosGenericos.ej8;

public class Main {
    public static void main(String[] args) {
        Kruger parqueNacional=new Kruger();
        Babuinos[]b=new Babuinos[15];
        for(int i=1 ;i<b.length ;i++){
           if(i % 2 == 0){
              b[i]= new Babuinos(parqueNacional,'D');
           }else{
              b[i]= new Babuinos(parqueNacional,'I');
           }
            
        }
        Thread []hilos=new Thread[b.length];
        for(int i=0; i< hilos.length ;i++){
            hilos[i]=new Thread(b[i], "m"+i);
        }
        for(int i=0; i< hilos.length ;i++){
            hilos[i].start();
        }
       
    }
}
