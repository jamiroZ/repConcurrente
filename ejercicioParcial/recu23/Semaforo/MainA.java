package ejercicioParcial.recu23.Semaforo;

public class MainA {
    public static void main(String[] args) {
        Casa casa =new Casa();
        AnimalMayor a=new AnimalMayor(casa);
        Thread animalMayor=new Thread(a,"animalMayor");

        Animalitos []b=new Animalitos[7];
        Thread []animalitos=new Thread[b.length];

        for (int i = 0; i < b.length; i++) {
                 b[i]=new Animalitos(casa);
                 animalitos[i]=new Thread(b[i], "animalito-"+i);
        }
        //
        animalMayor.start();
        for (int i = 0; i < b.length; i++) {    
               animalitos[i].start();
        }
        
    }
}
