package TP__FINAL.CarpetaObsCompartidos;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

import javax.crypto.spec.RC2ParameterSpec;

public class AreaDePremios {
    private final Exchanger<String> fichaEx;
    private final Exchanger<String> puntosEx;
    private final Exchanger<String> premioEx;

    private  Semaphore semEntrada;
    private Semaphore semEncargado;
    public AreaDePremios() {
        fichaEx = new Exchanger<String>();
        puntosEx = new Exchanger<String>();
        premioEx=new Exchanger<String>();
        semEntrada = new Semaphore(1);// asegura que un visitante notifique a un encargado para cambiar fichas
        semEncargado= new Semaphore(0);
    }
    public void pedirFicha() throws InterruptedException{
        semEntrada.acquire();//exclusion un visitante a la ves puede pedir ficha
            String visitante="visitante "+Thread.currentThread().getName();
            System.out.println(visitante+" espera una ficha");
            semEncargado.release();//avisa al encargado que lo necesita
            String ficha=fichaEx.exchange(visitante);//el visitante pide una ficha
            System.out.println(visitante+" obtuvo una "+ficha);
        semEntrada.release();//libera el semaforo para que otro visitante pueda pedir ficha
    }
    public void darFicha() throws InterruptedException {
        // metodo ejecutado por los encargados del area
      
        semEncargado.acquire();
            System.out.println("xxx");
            String visitante = fichaEx.exchange("ficha");//el encargado da una ficha
            System.out.println("el encargado da ficha a "+visitante);

    }
    public void jugarYrecibirPremio() throws InterruptedException {
        Random ran=new Random();
        int puntos = ran.nextInt(101);//numero random entre 0 y 100
        String puntosCast=String.valueOf(puntos);
        puntosEx.exchange(puntosCast);//le da los puntos al encargado
        String premio=premioEx.exchange(null);//el visitante espera su premio del encargado
        System.out.println("visitante "+Thread.currentThread().getName()+" obtiene por "+puntosCast+" puntos el premio "+premio);
    }
    public synchronized void darPremio()throws InterruptedException{
        String puntos=puntosEx.exchange(null);//obtiene los puntos del visitante
        Integer puntosCast=Integer.valueOf(puntos);//casteo a entero el string
        String premio="NADA";
        if(puntosCast >= 90){
            premio="OSO GIGANTE";
        }else if(puntosCast >= 50){
            premio="ROMPE MUELAS";
        }else if(puntosCast>=25){
            premio="CARAMELOS";
        }
        premioEx.exchange(premio);//el encargado le da el premio al visitante
    }
   
}
