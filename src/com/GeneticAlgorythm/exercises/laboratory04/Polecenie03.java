public class Polecenie03 {
    public static void main(String[] args) {
        AlgorytmGenetyczny algo = new AlgorytmGenetyczny(2,-2,2,3,0.1);
        Integer[] r1 = algo.generujPermutację(1,10);
        Integer[] r2 = algo.generujPermutację(1,10);

        for (Integer i:
             r1) {
            System.out.print(i);
        }
        System.out.println();
        for (Integer i:
                r2) {
            System.out.print(i);
        }
        System.out.println();

        Integer[][] potomkowie = algo.PMX(r1,r2);

        for (int i = 0; i < 2; i++) {
            for (Integer j:
                 potomkowie[i]) {
                if(j == null){
                    System.out.print("X");
                }
                else
                System.out.print(j);
            }
            System.out.println();
        }

    }
}
