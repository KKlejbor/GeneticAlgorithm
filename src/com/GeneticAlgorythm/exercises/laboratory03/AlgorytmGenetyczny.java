import java.util.Random;

public class AlgorytmGenetyczny {
    private int m; //długość genu
    private int ile; //ilość genów
    private double a; //dolna granica dziedziny
    private double b; //górna granica dziedziny
    private final Random losowanie = new Random();

    public AlgorytmGenetyczny() {

    }

    public AlgorytmGenetyczny(int ile, double min, double max, int przecinek) {
        this.m = (int) Math.ceil(log2((max - min) * Math.pow(10, przecinek)));
        this.ile = ile;
        this.a = min;
        this.b = max;
    }

    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getIle() {
        return ile;
    }

    public void setIle(int ile) {
        this.ile = ile;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public Integer[] generuj() {
        Integer[] wynik = new Integer[this.m * this.ile];
        Random losowanie = new Random();
        for (int i = 0; i < this.m * this.ile; i++) {
            wynik[i] = losowanie.nextInt(2);
        }
        return wynik;
    }

    public Double[] dekoduj(Integer[] tab) {
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

    public Integer[][] krzyżowaniePunktowe(Integer[] rodzic1, Integer[] rodzic2) {
        int indeks = losowanie.nextInt(ile * m);
        Integer[] potomek1 = new Integer[ile * m];
        Integer[] potomek2 = new Integer[ile * m];

        for (int i = 0; i < indeks; i++) {
            potomek1[i] = rodzic1[i];
            potomek2[i] = rodzic2[i];
        }

        for (int i = indeks; i < ile * m; i++) {
            potomek1[i] = rodzic2[i];
            potomek2[i] = rodzic1[i];
        }

        return new Integer[][]{potomek1, potomek2};
    }

    public Integer[][] krzyżowanieWielpunktowe(Integer[] rodzic1, Integer[] rodzic2) {
        Integer[] potomek1 = new Integer[this.ile * this.m];
        Integer[] potomek2 = new Integer[this.ile * this.m];

        int indeks1 = losowanie.nextInt(ile * m);
        int indeks2 = losowanie.nextInt(ile * m - (indeks1 + 1)) + (indeks1 + 1);

        for (int i = 0; i < indeks1; i++) {
            potomek1[i] = rodzic1[i];
            potomek2[i] = rodzic2[i];
        }

        for (int i = indeks1; i < indeks2; i++) {
            potomek1[i] = rodzic2[i];
            potomek2[i] = rodzic1[i];
        }

        for (int i = indeks2; i < this.ile * this.m; i++) {
            potomek1[i] = rodzic1[i];
            potomek2[i] = rodzic2[i];
        }

        return new Integer[][]{potomek1, potomek2};
    }
}
