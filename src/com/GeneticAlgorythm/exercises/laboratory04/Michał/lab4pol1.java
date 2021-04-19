package  Michał;
public class lab4pol1 {

    static  int[] chromosom;
    static double[] x;

    public static double Func(double x1, double x2)
    {
        return - Math.pow(x1,2) - Math.pow(x2,2) + 2;     //wzór funkcji
    }

    public static void main(String[] args) {

        geneticAlg Alg1 = new geneticAlg(-2,2,5,2);

        chromosom = Alg1.gen_ch();
        x = Alg1.dekodowanie(chromosom);
        System.out.print("Ciąg pierwotny: ");
        for (int x:chromosom) {
            System.out.print(x);
        }
        System.out.println();
        System.out.print("Wartość funkcji dla ciągu pierwotnego: ");
        System.out.println(Func(x[0],x[1]));
        System.out.println("Zmutowane bity: " + Alg1.mutation(chromosom, 0.1));
        System.out.print("Ciąg zmutowany: ");
        for (int x:chromosom) {
            System.out.print(x);
        }
        System.out.println();
        x = Alg1.dekodowanie(chromosom);
        System.out.print("Wartość funkcji dla ciągu zmutowanego: ");
        System.out.println(Func(x[0],x[1]));
    }
}
