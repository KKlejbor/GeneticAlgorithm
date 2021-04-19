import java.util.Arrays;

public class Polecenie01 {
    private static final int N = 10;

    private static double f(Double[] X) {
        return -Math.pow(X[0], 2) - Math.pow(X[1], 2) + 2;
    }

    private static Double[] obliczWartości(Integer[][] populacja, AlgorytmGenetyczny algo) {
        Double[] wyniki = new Double[N];

        for (int i = 0; i < N; i++) {
            wyniki[i] = f(algo.dekoduj(populacja[i]));
        }

        return wyniki;
    }

    private static void drukujChromosomy(Integer[][] populacja) {
        for (int i = 0; i < N; i++) {
            Drukowanie.drukujTabBin(populacja[i]);
        }
    }

    private static void drukujWartości(Integer[][] populacja, AlgorytmGenetyczny algo) {
        Double[] wynik = obliczWartości(populacja, algo);

        for (int i = 0; i < N; i++) {
            Drukowanie.drukujWartośćFunkcji(algo.dekoduj(populacja[i]), wynik[i]);
        }
    }

    private static void drukujWartościOrazChromosomy(Integer[][] populacja, AlgorytmGenetyczny algo) {
        Double[] wynik = obliczWartości(populacja, algo);

        for (int i = 0; i < N; i++) {
            System.out.printf("Element %d. populacji\n",i);
            Drukowanie.drukujTabBin(populacja[i]);
            System.out.println();
            Drukowanie.drukujWartośćFunkcji(algo.dekoduj(populacja[i]), wynik[i]);
        }
    }

    public static double średniaWartość(Integer[][] populacja, AlgorytmGenetyczny algo){
        double średnia = 0;
        Double[] wartości = obliczWartości(populacja,algo);

        for (int i = 0; i < N; i++) {
            średnia += wartości[i];
        }

        return średnia / N;
    }

    public static int ileWiększych(Integer[][] populacja,AlgorytmGenetyczny algo){
        double średnia = średniaWartość(populacja,algo);
        int ile = 0;
        Double[] wyniki = obliczWartości(populacja,algo);

        for (int i = 0; i < N; i++) {
            if(wyniki[i] >= średnia)
            ile++;
        }
        return ile;
    }

    public static int ileMniejszych(Integer[][] populacja,AlgorytmGenetyczny algo){
        double średnia = średniaWartość(populacja,algo);
        int ile = 0;
        Double[] wyniki = obliczWartości(populacja,algo);

        for (int i = 0; i < N; i++) {
            if(wyniki[i] < średnia)
                ile++;
        }
        return ile;
    }

    public static void main(String[] args) {
        AlgorytmGenetyczny algo = new AlgorytmGenetyczny(2, -2, 2, 5, 0.1);

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
