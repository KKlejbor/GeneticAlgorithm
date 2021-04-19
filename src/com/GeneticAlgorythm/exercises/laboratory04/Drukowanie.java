public class Drukowanie {
    public static void drukujTabBin(Integer[] tab) {
        for (Integer integer : tab) {
            System.out.print(integer);
        }
        System.out.println();
    }

    public static void drukujWartośćFunkcji(Double[] X, Double Y) {
        System.out.printf("F(X) = %f\n", Y);
        System.out.println("Dla:");
        for (int i = 0; i < X.length; i++) {
            System.out.printf("\tx%d = %f\n", i, X[i]);
        }
    }

    public static int ileuległoZmianie(Integer[] tab1, Integer[] tab2){
        int ile = 0;
        for (int i = 0; i < tab1.length; i++) {
            if(tab1[i]!=tab2[i])
                ile++;
        }
        return ile;
    }
}
