package TP4_SemaforosBinarios.ej7;

public class TestMain {
    public static void main(String[] args) {
          Confiteria conf=new Confiteria();//objeto compartido
          Empleado[]empleados=new Empleado[10];
          Thread[]hilos=new Thread[empleados.length];
          
          for(int i=0; i<empleados.length;i++){
                empleados[i]=new Empleado(conf, i);
          }
          for(int i=0; i< hilos.length ;i++){
                hilos[i]=new Thread(empleados[i] , "LAW"+i);
          }
          Mozo mozo=new Mozo(conf);
          Thread m=new Thread(mozo);
          //HILOS LISTOS
          for(int i=0; i< hilos.length ;i++){
            hilos[i].start();
          }
          m.start();
          try {
              m.join();
              for(int i=0; i< hilos.length ;i++){
                    hilos[i].join();
              }
          } catch (Exception e) {
            // TODO: handle exception
          }
         
    }
}
