import java.util.Random;

public class Polecenie02 {
    private static final int m = 14; //długość genu
    private static final int p = 3; //ilość liczb po przecinku
    private static final int ile = 10; //ilość genów
    private static final double a = -5.21; //dolna granica dziedziny
    private static final double b = 5.21; //górna granica dziedziny
    private static final int A = 10; //amplituda
    private static final int n = 10; //Nie mam zielonego pojęcia

    private static double f(Double[] X) {
        double wynik = A * n;
        for (int i = 0; i < n; i++) {
            wynik += Math.pow(X[i], 2) - A * Math.cos(2 * Math.PI * X[i]);
        }
        return wynik;
    }

    private static Double[] dekoduj(Integer[] tab) {
        double liczba;
        Double[] wynik = new Double[ile];
        for (int i = 0; i < ile; i++) {
            liczba = 0;
            for (int j = m * (1 + i) - 1; j >= i * m; j--) {
                liczba += tab[j] * Math.pow(2, j - i * m);
            }
            wynik[i] = a + liczba * ((b - a) / (Math.pow(2, m) - 1));
        }
        return wynik;
    }

    private static Integer[] generuj() {
        Integer[] wynik = new Integer[m * ile];
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
        } while (Xdec[0] > 5.21 || Xdec[0] < -5.21 ||
                Xdec[1] > 5.21 || Xdec[1] < -5.21 ||
                Xdec[2] > 5.21 || Xdec[2] < -5.21 ||
                Xdec[3] > 5.21 || Xdec[3] < -5.21 ||
                Xdec[4] > 5.21 || Xdec[4] < -5.21 ||
                Xdec[5] > 5.21 || Xdec[5] < -5.21 ||
                Xdec[6] > 5.21 || Xdec[6] < -5.21 ||
                Xdec[7] > 5.21 || Xdec[7] < -5.21 ||
                Xdec[8] > 5.21 || Xdec[8] < -5.21 ||
                Xdec[9] > 5.21 || Xdec[9] < -5.21);

        F = f(Xdec);

        System.out.printf("F(X) = %f\n", F);
        System.out.println("Dla:");
        System.out.printf("\tx1 = %f\n", Xdec[0]);
        System.out.printf("\tx2 = %f\n", Xdec[1]);
        System.out.printf("\tx3 = %f\n", Xdec[2]);
        System.out.printf("\tx4 = %f\n", Xdec[3]);
        System.out.printf("\tx5 = %f\n", Xdec[4]);
        System.out.printf("\tx6 = %f\n", Xdec[5]);
        System.out.printf("\tx7 = %f\n", Xdec[6]);
        System.out.printf("\tx8 = %f\n", Xdec[7]);
        System.out.printf("\tx9 = %f\n", Xdec[8]);
        System.out.printf("\tx10 = %f\n", Xdec[9]);
    }
}
