package TP5_SemaforosGenericos.ej2;

public class Main {
    public static void main(String[] args) {
        Cafeteria conf=new Cafeteria();//objeto compartido
        Empleado[]empleados=new Empleado[6];
        Thread[]hilos=new Thread[empleados.length];
        
        for(int i=0; i<empleados.length;i++){//empleados
              int alt=(1+(int)(Math.random()*3));
              switch (alt) {
                case 1:
                       empleados[i]=new Empleado('a',conf);
                break;

                case 2:
                    empleados[i]=new Empleado('c',conf);
                break;

                case 3:
                    empleados[i]=new Empleado('b',conf);
                 break;
                default:
                break;
              }
        
        }
        for(int i=0; i< hilos.length ;i++){//empleados en hilos
              hilos[i]=new Thread(empleados[i] , "e"+i);
        }

        //mozo y cocinero 
        Mozo mozo=new Mozo(conf);
        Cocinero cocinero=new Cocinero(conf);
        //creo hilos de cocinero y mozo
        Thread mozoH=new Thread(mozo);
        Thread cocineroH=new Thread(cocinero);
    
        //HILOS LISTOS
        for(int i=0; i< hilos.length ;i++){
          hilos[i].start();
        }

        mozoH.start();
        cocineroH.start();
        /*  try {
            mozoH.join();
            for(int i=0; i< hilos.length ;i++){
                  hilos[i].join();
            }
        } catch (Exception e) {
          // TODO: handle exception
        }*/
    }
}
