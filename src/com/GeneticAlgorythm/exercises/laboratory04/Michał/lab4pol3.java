package  Michał;
public class lab4pol3 {
    static int[] R1 = {1,2,3,4,5,6,7,8,9,10};
    static int[] R2 = {1,2,3,4,5,6,7,8,9,10};
    static int[][] potomki;

    public static void main(String[] args) {
        geneticAlg.generujPermu(R1);
        geneticAlg.generujPermu(R2);

        System.out.print("R1: ");
        for (int x:R1) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.print("R2: ");
        for (int x:R2) {
            System.out.print(x + " ");
        }
        System.out.println();
        potomki = geneticAlg.pmx(R1,R2);
        System.out.print("Potomek 1: ");
        for (int x:potomki[0]) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.print("potomek 2: ");
        for (int x:potomki[1]) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
