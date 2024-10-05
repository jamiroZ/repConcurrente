package TP5_SemaforosGenericos.ej3;

public class Main {
    public static void main(String[] args) {
        Comedero obj=new Comedero(3);//objeto compartido
        //hilos de gatos y perros
        int cantP=9 ,cantG=9;
        Perro []firulais=new Perro[cantP];
        Gato []michis=new Gato[cantG];
        //uso el for para cargarles el objeto comp a los 2
        for(int i=0; i< cantP ; i++){
            firulais[i]=new Perro(obj);
            
            michis[i]=new Gato(obj);
            
        }
        //preparo hilos 
        Thread [] gato =new Thread[cantG];
        Thread [] perro =new Thread[cantP];
        
        for(int i=1 ; i < cantP ; i++){
              perro[i]= new Thread(firulais[i] ,"p"+i);
              gato[i]=new Thread(michis[i] ,"g"+i);

        }
        //hilos listos
        
        for(int i=1 ; i < cantP ; i++){//
            
            perro[i].start();       
            gato[i].start();
               
        }
    

    }

}
