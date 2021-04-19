import java.util.Arrays;

public class Polecenie01 {
    private static double f(Double[] X) {
        return -Math.pow(X[0], 2) - Math.pow(X[1], 2) + 2;
    }

    public static void main(String[] args) {
        AlgorytmGenetyczny algo = new AlgorytmGenetyczny(2, -2, 2, 5, 0.1);

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
