package TP8.ej2;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
         Observatorio obs=new Observatorio(50);
         Visitante[]v=new Visitante[100];
         Mantenimiento[]m=new Mantenimiento[20];
         Investigador[]i=new Investigador[10];

         for (int j = 0; j < v.length; j++) {
            if(j%24==0){
               v[j]=new Visitante(obs, true);
            }else{
                v[j]=new Visitante(obs, false);
            }
         }
         for (int j = 0; j < m.length; j++) {
            m[j]=new Mantenimiento(obs);
         }
         for (int j = 0; j < i.length; j++) {
            i[j]=new  Investigador(obs);
         }

         Thread[]visitantes=new Thread[v.length];
         Thread[]mantenimientos=new Thread[m.length];
         Thread[]investigadores=new Thread[i.length];
         for (int j = 0; j < visitantes.length; j++) {
             visitantes[j]=new Thread(v[j], "visitante-"+j);
         }
         for (int j = 0; j <mantenimientos.length; j++) {
             mantenimientos[j]=new Thread(m[j], "trabajador-"+j);
         }
         for (int j = 0; j < investigadores.length; j++) {
             investigadores[j]=new Thread(i[j], "investigador-"+j);
         }
         for (int j = 0; j < visitantes.length; j++) {
              visitantes[j].start();
              if(j < investigadores.length){
                  investigadores[j].start();
              }
              if(j < mantenimientos.length){
                mantenimientos[j].start();
              }
         }
         

    }
}
