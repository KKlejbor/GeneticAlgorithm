import java.util.Arrays;

public class Polecenie02 {
    private static final int N = 10;
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

        Integer[][] chromosomy = algo.generujPopulację(N);
        System.out.println("Wartości chromosomów: ");
        drukujWartości(chromosomy,algo);
        System.out.println("Wartości chromosomów wraz wartościami funkcji");

        int ileMniejszych = ileMniejszych(chromosomy,algo);
        int ileWiększych = ileMniejszych(chromosomy,algo);
        double wartośćŚrednia = średniaWartość(chromosomy, algo);
        drukujWartościOrazChromosomy(chromosomy,algo);

        System.out.printf("W populacji znajduje się %d waartości mniejszych od średniej %f\n",ileMniejszych,wartośćŚrednia);
        System.out.printf("W populacji znajduje się %d waartości mniejszych od średniej %f\n",ileWiększych,wartośćŚrednia);
    }
}
