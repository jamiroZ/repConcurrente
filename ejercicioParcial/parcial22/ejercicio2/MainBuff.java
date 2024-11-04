package ejercicioParcial.parcial22.ejercicio2;

public class MainBuff {
    public static void main(String[] args) {
        Buffer b=new Buffer();//objeto compartido
        Extractor[]ex=new Extractor[2];
        Insertor[]ins=new Insertor[2];
        //hilos
        Thread[]extractores=new Thread[ex.length];
        Thread[]insertores=new Thread[ins.length];
        for(int i=0;i<ex.length + ins.length;i++){
           if(i < ex.length ){
               ex[i]=new Extractor(b);
               extractores[i]=new Thread(ex[i],"extractor-"+i);
           }
           if(i < ins.length){
               ins[i]=new Insertor(b);
               insertores[i]=new Thread(ins[i],"insertor-"+i);
           }
       
        }
        for(int i=0;i<ex.length + ins.length;i++){
            if(i < ex.length ){             
                extractores[i].start();
            }
            if(i < ins.length){
                insertores[i].start();
            } 
         }
    }
}
