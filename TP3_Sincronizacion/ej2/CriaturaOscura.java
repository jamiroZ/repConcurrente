package TP3_Sincronizacion.ej2;

public class CriaturaOscura extends Thread{
       private Energia obj;//objeto compartido
       public CriaturaOscura(Energia obj){
            this.obj=obj;
       }
       public void run(){
           obj.restarEnergia(3);
       }
}
