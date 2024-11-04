package ejercicioParcial.recu23.lock;
import java.util.Random;
public  class Main {
    public static void main(String[] args) {
        Random r=new Random();
        Serie  serie = new Serie();
        Filmador f=new Filmador(serie, 6);
        Thread filmador=new Thread(f, "Filmador");
       
        Traductor[]t=new Traductor[2];
        Thread[]traductorHilo=new Thread[2];
        Socio[]s=new Socio[10];
        Thread[]sociosHilo=new Thread[s.length];

        for (int i = 0; i < s.length + 2; i++) {
            if( i< t.length){
                t[i]=new Traductor(serie);
                traductorHilo[i]=new Thread(t[i], "Traductor"+i);

            }
            if( i < s.length){
                int variado=r.nextInt(2);
                if(i % 2==0){
                    s[i]=new Socio(serie,true);
                }else{
                    s[i]=new Socio(serie,false);
                }
                
                sociosHilo[i]=new Thread(s[i], "Socio"+i);
            }
        }
        filmador.start();
        for (int i = 0; i < sociosHilo.length +  2; i++) {
             if(i< traductorHilo.length){
                traductorHilo[i].start();
             }
             if(i<sociosHilo.length){
                sociosHilo[i].start();
             }
        }
    }
}
