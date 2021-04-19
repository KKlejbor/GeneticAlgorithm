public class Polecenie02 {
    private static final int A = 10; //amplituda
    private static final int n = 10; //ilość zmiennych

    private static double f(Double[] X) {
        double wynik = A * n;
        for (int i = 0; i < n; i++) {
            wynik += Math.pow(X[i], 2) - A * Math.cos(2 * Math.PI * X[i]);
        }
        return wynik;
    }

    public static void main(String[] args) {
        //deklaracja obiektu klasy algorytmGenetyczny
        AlgorytmGenetyczny algo = new AlgorytmGenetyczny(10, -5.21, 5.21, 3);

        //deklaracja tablic przechowujących ciągi binarne
        Integer[] rodzic1, rodzic2, potomek1Bin, potomek2Bin;

        //deklaracja tablic przechowujących liczby rzeczywiste
        Double[] potomek1Dec, potomek2Dec;

        //macierz przechowująca skrzyżowanych potomków
        Integer[][] potmkowieBin;

        //generowanie ciągów binarnych dla rodziców
        rodzic1 = algo.generuj();
        rodzic2 = algo.generuj();

        //drukowanie ciągów rodziców
        System.out.print("r1: ");
        Drukowanie.drukujTabBin(rodzic1);
        System.out.print("r2: ");
        Drukowanie.drukujTabBin(rodzic2);

        potmkowieBin = algo.krzyżowaniePunktowe(rodzic1,rodzic2);

        potomek1Bin = potmkowieBin[0];
        potomek2Bin = potmkowieBin[1];

        //drukowanie ciągów potomków
        System.out.print("p1: ");
        Drukowanie.drukujTabBin(potomek1Bin);
        System.out.print("p2: ");
        Drukowanie.drukujTabBin(potomek2Bin);

        //zamiana ciągów binarnych na liczby rzeczywiste
        potomek1Dec = algo.dekoduj(potomek1Bin);
        potomek2Dec = algo.dekoduj(potomek2Bin);

        System.out.println("\nKrzyżowanie punktowe:");

        Drukowanie.drukujWartośćFunkcji(potomek1Dec,f(potomek1Dec));

        System.out.println();

        Drukowanie.drukujWartośćFunkcji(potomek2Dec,f(potomek2Dec));

        System.out.println();
        System.out.println();

        //drukowanie ciągów rodziców
        System.out.print("r1: ");
        Drukowanie.drukujTabBin(rodzic1);
        System.out.print("r2: ");
        Drukowanie.drukujTabBin(rodzic2);

        potmkowieBin = algo.krzyżowanieWielpunktowe(rodzic1,rodzic2);

        potomek1Bin = potmkowieBin[0];
        potomek2Bin = potmkowieBin[1];

        //drukowanie ciągów potomków
        System.out.print("p1: ");
        Drukowanie.drukujTabBin(potomek1Bin);
        System.out.print("p2: ");
        Drukowanie.drukujTabBin(potomek2Bin);

        //zamiana ciągów binarnych na liczby rzeczywiste
        potomek1Dec = algo.dekoduj(potomek1Bin);
        potomek2Dec = algo.dekoduj(potomek2Bin);

        System.out.println("\nKrzyżowanie wielopunktowe:");

        Drukowanie.drukujWartośćFunkcji(potomek1Dec,f(potomek1Dec));

        System.out.println();

        Drukowanie.drukujWartośćFunkcji(potomek2Dec,f(potomek2Dec));
    }
}
