package TP4_SemaforosBinarios.ej8;

import java.util.concurrent.Semaphore;

public class ControladorProduccion {
        private Semaphore oeste;//productos mecanicos llegan desde el oeste
        private Semaphore norte;//productos electricos llegan desde el norte
        private Semaphore caja;//sensor
        public ControladorProduccion(){
            this.oeste=new Semaphore(0);
            this.norte=new Semaphore(0);
            this.caja=new Semaphore(1);
        }
        public void llegaElectrico(){
            try {
                caja.acquire();//
                System.out.println(Thread.currentThread().getName()+"entro a la linea");
            } catch (Exception e) {
                // TODO: handle exception
            }
            
        }
        public void llegaMecanico(){
            try {
                caja.acquire();//
                System.out.println(Thread.currentThread().getName()+"entro a la linea");
            } catch (Exception e) {
                // TODO: handle exception
            }
            
        }
        public void sale(){
           
            System.out.println(Thread.currentThread().getName()+"salio de la linea");
            this.caja.release();
        }
}
