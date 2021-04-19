package  Michał;
public class lab4pol2 {

    static  int[] chromosom;
    static double[] x;
    static int A=10;
    static int N=10;

    public static double func()
    {
        double suma = 0;
        for (double w:x)
        {
            suma += Math.pow(w,2) - A * Math.cos(2*Math.PI*w);
        }
        return A*N + suma;
    }


    public static void main(String[] args) {

        geneticAlg Alg1 = new geneticAlg(-5.21,5.21,3,10);

        chromosom = Alg1.gen_ch();
        x = Alg1.dekodowanie(chromosom);
        System.out.print("Ciąg pierwotny: ");
        for (int x:chromosom) {
            System.out.print(x);
        }
        System.out.println();
        System.out.print("Wartość funkcji dla ciągu pierwotnego: ");
        System.out.println(func());
        System.out.println("Zmutowane bity: " + Alg1.mutation(chromosom, 0.1));
        System.out.print("Ciąg zmutowany: ");
        for (int x:chromosom) {
            System.out.print(x);
        }
        System.out.println();
        x = Alg1.dekodowanie(chromosom);
        System.out.print("Wartość funkcji dla ciągu zmutowanego: ");
        System.out.println(func());
    }
}
