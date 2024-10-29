package TP8.ej2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Observatorio {
     private int capacidad;

     private int visitanteDentro=0;
     private int investigadorDentro=0;
     private int mantenimientoDentro=0;

     private Lock  lock = new ReentrantLock();
     private Condition hayVisitantes= lock.newCondition();
     private Condition hayInvestigadores=lock.newCondition();
     private Condition hayMantenimiento=lock.newCondition();
     
     public Observatorio(int capacidad){
        this.capacidad = capacidad;
         this.visitanteDentro=capacidad;
         this.investigadorDentro=capacidad;
         this.mantenimientoDentro=capacidad;
     }
     public void entrarVisitante(Boolean silla) throws  InterruptedException {
        lock.lock();
        try {

            //si hay investigadores o mantenimiento o esta lleno espera
            while(this.visitanteDentro==0 || this.mantenimientoDentro < this.capacidad || this.investigadorDentro < this.capacidad ){
               // System.out.println("visi "+investigadorDentro+" "+mantenimientoDentro+" "+visitanteDentro);
                this.hayVisitantes.await();
            }
            System.out.println(Thread.currentThread().getName()+"  entra al observatorio");
            this.visitanteDentro--;//ocupa un espacio 
            
            if(silla){//es un visitante en silla de ruedas
                this.capacidad=30;//se limita la capacidad hasta que salga
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }

     }
     public void salirVisitante(Boolean silla) throws  InterruptedException {
        lock.lock();
        try {
            this.visitanteDentro++;//libera un espacio del observatorio
          
            if(silla){
                System.out.println(Thread.currentThread().getName()+" en silla salio del observatorio");
                this.capacidad=50;//aumenta la capacidad 
            }else{
                System.out.println(Thread.currentThread().getName()+"  salio del observatorio");
            }

            if(this.visitanteDentro==this.capacidad){  //fue el ultimo en salir
                this.hayInvestigadores.signalAll();
                this.hayMantenimiento.signalAll();
            }else{
                this.hayVisitantes.signalAll();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }

     }

     public void entrarInvestigador() throws  InterruptedException {
        lock.lock();
        try {

            //si ya esta lleno de investigadores o hay visitantes o mantenimiento espera
            while(this.investigadorDentro==0 || this.mantenimientoDentro < this.capacidad  || this.visitanteDentro < this.capacidad || this.mantenimientoDentro < this.capacidad        ){  

               // System.out.println("invi "+investigadorDentro+" "+mantenimientoDentro+" "+visitanteDentro);
                this.hayInvestigadores.await();
            }
            System.out.println(Thread.currentThread().getName()+"  entra al observatorio");
            this.investigadorDentro--;
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }

     }
     public void salirInvestigador() throws  InterruptedException {
        lock.lock();
        try {
           this.investigadorDentro++;//libero espacio para investigador
           System.out.println(Thread.currentThread().getName()+" salio del observatorio");
             
           //si fue el ultimo investigador en salir
           if(this.investigadorDentro==this.capacidad){
              this.hayMantenimiento.signalAll();
              this.hayVisitantes.signalAll();
           }else{
              this.hayInvestigadores.signalAll();
           }
              
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }

     }


     public void entrarMantenimiento() throws  InterruptedException {
        lock.lock();
        try {
            //si esta lleno de mantenimiento o hay visitantes o investigadores
            while(this.mantenimientoDentro==0 ||this.investigadorDentro< this.capacidad || this.visitanteDentro < this.capacidad){
               // System.out.println("mante "+investigadorDentro+" "+mantenimientoDentro+" "+visitanteDentro);
                this.hayMantenimiento.await();
            }
            System.out.println(Thread.currentThread().getName()+"  entra al observatorio");
            this.mantenimientoDentro--;
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }

     }
     public void salirMantenimiento() throws  InterruptedException {
        lock.lock();
        try {
            this.mantenimientoDentro++;
            System.out.println(Thread.currentThread().getName()+"  salio del observatorio");
            
            //si fue el ultimo de mantenimiento en salir
            if(this.mantenimientoDentro==this.capacidad){
                this.hayInvestigadores.signalAll();
                this.hayVisitantes.signalAll();
            }else{
                this.hayMantenimiento.signalAll();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
     }
}
