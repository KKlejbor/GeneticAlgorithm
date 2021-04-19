import java.util.Arrays;

public class Polecenie02 {
    private static final int A = 10;
    private static final int n = 10;

    private static double f(Double[] X) {
        double wynik = A * n;
        for (int i = 0; i < n; i++) {
            wynik += Math.pow(X[i], 2) - A * Math.cos(2 * Math.PI * X[i]);
        }
        return wynik;
    }

    public static void main(String[] args) {
        AlgorytmGenetyczny algo = new AlgorytmGenetyczny(10, -5.21, 5.21, 3, 0.1);

        Integer[] chromosom = algo.generuj();
        Double[] X = algo.dekoduj(chromosom);
        Integer[] przedmutacją = Arrays.copyOf(chromosom,chromosom.length);

        System.out.print("Przed mutacją: ");
        Drukowanie.drukujTabBin(chromosom);
        Drukowanie.drukujWartośćFunkcji(X, f(X));
        System.out.println();

        chromosom = algo.mutacja(chromosom);
        X = algo.dekoduj(chromosom);

        System.out.print("Po mutacją: ");
        Drukowanie.drukujTabBin(chromosom);
        Drukowanie.drukujWartośćFunkcji(X, f(X));

        System.out.printf("\nZmianie uległo %d bitów",Drukowanie.ileuległoZmianie(przedmutacją,chromosom));
    }
}
