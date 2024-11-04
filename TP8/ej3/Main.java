package TP8.ej3;

public class Main {
    public static void main(String[] args) {
        int pesoA=400;
        int pesoB=600;
        int pesoC=500;
        Pasteleria pasteleria=new Pasteleria(1400,pesoA,pesoB, pesoC);
        Horno []h=new Horno[3];
        h[0]=new Horno(pasteleria,5, pesoA);
        h[1]=new Horno(pasteleria, 7, pesoB);
        h[2]=new Horno(pasteleria, 9,pesoC);
        

    }
}
