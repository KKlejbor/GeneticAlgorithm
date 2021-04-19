import java.util.*;

public class AlgorytmGenetyczny {
    private final Random losowanie = new Random();
    private int m; //długość genu
    private int ile; //ilość genów
    private double a; //dolna granica dziedziny
    private double b; //górna granica dziedziny
    private double p; //prawdopodobieństwo mutacji

    public AlgorytmGenetyczny() {

    }

    public AlgorytmGenetyczny(int ile, double min, double max, int przecinek, double prawdopodobieństwo) {
        m = (int) Math.ceil(log2((max - min) * Math.pow(10, przecinek)));
        this.ile = ile;
        a = min;
        b = max;
        p = prawdopodobieństwo;
    }

    public AlgorytmGenetyczny(int ile, double min, double max, int przecinek) {
        m = (int) Math.ceil(log2((max - min) * Math.pow(10, przecinek)));
        this.ile = ile;
        a = min;
        b = max;
        p = 0.1;
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

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
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

    public Integer[] mutacja(Integer[] tablica) {
        for (int i = 0; i < tablica.length; i++) {
            if (losowanie.nextDouble() <= p) {
                if (tablica[i] == 0)
                    tablica[i] = 1;
                else
                    tablica[i] = 0;
            }
        }
        return tablica;
    }

    public Integer[] generujPermutację(int min, int max) {
        Integer[] tab = new Integer[max - min];
        ArrayList<Integer> wykorzystano = new ArrayList<>();
        int i = 0;
        int liczba;

        while (i < tab.length) {
            liczba = losowanie.nextInt(max - min) + min;
            if (!wykorzystano.contains(liczba)) {
                tab[i] = liczba;
                wykorzystano.add(liczba);
                i++;
            }
        }

        return tab;
    }

    public Integer[][] PMX(Integer[] rodzic1, Integer[] rodzic2){
        Integer[][] potomkowie = new Integer[2][rodzic1.length];
        ArrayList<Integer> powtórzenia1 = new ArrayList<>();
        ArrayList<Integer> powtórzenia2 = new ArrayList<>();
        Map<Integer,Integer> odwzorowania1 = new HashMap<>();
        Map<Integer,Integer> odwzorowania2 = new HashMap<>();
        int indeks1 = losowanie.nextInt(rodzic1.length);
        int indeks2;
        do{
            indeks2 = losowanie.nextInt(rodzic1.length);
        }while (indeks1>=indeks2);

        for (int i = indeks1; i <= indeks2; i++) {
            potomkowie[0][i] = rodzic2[i];
            powtórzenia1.add(rodzic2[i]);
            potomkowie[1][i] = rodzic1[i];
            powtórzenia2.add(rodzic1[i]);
            odwzorowania1.put(rodzic1[i],rodzic2[i]);
            odwzorowania2.put(rodzic2[i],rodzic1[i]);
        }

        for (int i = 0; i < rodzic1.length; i++) {
            if(potomkowie[0][i] == null && !powtórzenia1.contains(rodzic1[i])){
                potomkowie[0][i] = rodzic1[i];
                powtórzenia1.add(rodzic1[i]);
            }
            if(potomkowie[1][i] == null && !powtórzenia2.contains(rodzic2[i])){
                potomkowie[1][i] = rodzic2[i];
                powtórzenia2.add(rodzic2[i]);
            }
        }

        for (int i = 0; i < rodzic1.length; i++) {
            if(potomkowie[0][i] == null) {
                for (int j = indeks1; j < indeks2; j++) {
                    if(!powtórzenia1.contains(odwzorowania1.get(rodzic1[j]))){
                        potomkowie[0][i] = odwzorowania1.get(rodzic1[j]);
                    }
                    else
                        potomkowie[0][i] = odwzorowania2.get(odwzorowania1.get(rodzic1[j]));
                }
            }

            if(potomkowie[1][i] == null) {
                for (int j = indeks1; j < indeks2; j++) {
                    if(!powtórzenia2.contains(odwzorowania2.get(rodzic2[j]))){
                        potomkowie[1][i] = odwzorowania2.get(rodzic2[j]);
                    }
                    else
                        potomkowie[1][i] = odwzorowania1.get(odwzorowania2.get(rodzic2[j]));
                }
            }
        }

        return potomkowie;
    }
}
