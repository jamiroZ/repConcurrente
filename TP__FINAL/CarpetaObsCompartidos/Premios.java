package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.sql.Time;
import java.util.Random;
public class Premios {
    private Exchanger<Integer> ficha=new Exchanger<>();
    private Exchanger<Integer> pausar=new Exchanger<>();
    public Premios(){

    }
    public synchronized void pedirFicha() throws InterruptedException {

        ficha.exchange(1);//pide una ficha o espera a que EL ENCARGADO le pase una
        System.out.println("visitante "+Thread.currentThread().getName()+" ha recibido una ficha");
        pausar.exchange(1);//avisa al encargado que ya tiene ficha
    }
    public void darFicha()throws InterruptedException{
        ficha.exchange(1);//da una ficha o espera a que algun VISITANTE quiera una
        System.out.println("--Encargado da ficha--");
        pausar.exchange(1);//espera a que el visitante tenga su ficha
    }
    public synchronized int jugarJuegos() throws InterruptedException{
        
        int puntos=(int)(Math.random()*10000)+1;//puede obtener de 1 a 10000 puntos de manera aleatoria
        System.out.println("visitante "+Thread.currentThread().getName()+" jugo y obtuvo "+puntos+" puntos");
        return puntos;
    
    }
    public synchronized void recibirPremio(int puntos) throws InterruptedException{
        if(puntos==10000){//el mejor premio 
                System.out.println("visitante "+Thread.currentThread().getName()+" ha ganado el mejor premio ");
        }else if(puntos>=5000 && puntos <10000){//intermedio
                System.out.println("visitante "+Thread.currentThread().getName()+" gano un premio regular");
        }else if(puntos<5000 && puntos >=1000){//minimo
                System.out.println("visitante "+Thread.currentThread().getName()+" gano el peor premio ");
        }else{
                System.out.println("No hay premio para menos de 1000 puntos");
        }       
    }
}
