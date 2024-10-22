package TP7_Locks.ej1;

public class Main {
    public static void main(String[] args) {
        Sala museo=new Sala();
        Persona[]p=new Persona[145];
        for (int i = 0; i < 145; i++) {
            if(i%10 ==0){//cada 10 personas hay un jubilado
                p[i]=new Persona(museo,true);
            }else{
                p[i]=new Persona(museo,false);
            }
        }
        MedidorTemp medidor= new MedidorTemp(museo);
        Thread []personas=new Thread[p.length];
        Thread temperatura= new Thread(medidor);
        for (int i = 0; i < p.length; i++) {
               personas[i]=new Thread(p[i],"P"+i);
        }
        temperatura.start();
        for (int i = 0; i < p.length; i++) {
             personas[i].start();
        }
    }
}
