import java.util.Random;

public class Polecenie01 {
    private static final int m = 19; //długość chromosomu
    private static final int p = 5; //ilość cyfr po przecinku
    private static final int ile = 2; //ilość genów
    private static final int a = -2; //dolna granica dziedziny
    private static final int b = 2; //górna granica dziedziny

    private static double f(double x1, double x2){
        return -Math.pow(x1,2) - Math.pow(x2,2) + 2;
    }

    private static Double[] dekoduj(Integer[] tab){
        double liczba;
        Double[] wynik = new Double[tab.length];
        for (int i = 0; i < ile; i++) {
            liczba = 0;
            for (int j = m * (1 + i) - 1; j >= i*m; j--) {
                liczba += tab[j] * Math.pow(2,j - i*m);
            }
            wynik[i] = a + liczba * ((b - a)/(Math.pow(2,m) - 1));
        }
        return wynik;
    }

    private static Integer[] generuj(){
        Integer[] wynik = new Integer[m*ile];
        Random losowanie = new Random();
        for (int i = 0; i < m * ile; i++) {
            wynik[i] = losowanie.nextInt(2);
        }
        return wynik;
    }

    public static void main(String[] args) {
        Integer[] Xbin; //ciąg X w zapisie binarnym
        Double[] Xdec; //wartości zmiennych x
        double F; //wartość funkcji

        do {
            Xbin = generuj();
            Xdec = dekoduj(Xbin);
        }while (Xdec[0] > 2 || Xdec[0] < -2 ||
                Xdec[1] > 2 || Xdec[1] < -2);

        F = f(Xdec[0],Xdec[1]);


        System.out.printf("F(X) = %f\n",F);
        System.out.println("Dla:");
        System.out.printf("\tx1 = %f\n",Xdec[0]);
        System.out.printf("\tx2 = %f\n",Xdec[1]);
    }
}
