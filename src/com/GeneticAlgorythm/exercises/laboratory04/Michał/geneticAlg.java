package  Michał;
import java.util.Random;

public class geneticAlg {

    private double min = -5.21;
    private double max = 5.21;
    private int dok = 3;
    private int N=10;

    public geneticAlg(double minX,double maxX, int dok, int n)
    {
        this.min = minX;
        this.max = maxX;
        this.dok = dok;
        this.N = n;
    }

    public int dlCh(int N, int dok) {
        return N * (int)  Math.ceil(log_a(2, (max - min) * Math.pow(10, dok)));
    }

    public double log_a(double a, double b)
    {
        return Math.log(b) / Math.log(a);
    }

    public int[] gen_ch()
    {
        int n = dlCh(N,dok);
        int[] a = new int[n];
        Random r = new Random();
        for(int i = 0; i<n;i++)
        {
            a[i] = r.nextInt(2);
        }
        return a;
    }

    public double dec(String binar)
    {
        double suma = 0;
        double pom =0;
        for(int i = 0; i<binar.length(); i++)
        {
            pom = Integer.parseInt(binar.subSequence(i,i+1).toString()) * Math.pow(2,binar.length() - 1 -i);
            suma += pom;
        }
        return suma;
    }

    public double[] dekodowanie(int[] chromosom)
    {
        int a = dlCh(1,dok);
        String pom="";
        int c = Math.round(chromosom.length/a);
        double[] tab = new double[c];
        double roz = (max-min)/(Math.pow(2,a) - 1);
        for(int i = 0;i < c; i++)
        {
            for(int k = i*a; k < (1+i)*a;k++)
            {
                pom += chromosom[k];
            }
            tab[i] = dec(pom) * roz + min;
            pom = "";
        }
        return tab;
    }

    public int[][] krzyżowanie(int[] chromosomA,int[] chromosomB)
    {
        int pk = new Random().nextInt(chromosomA.length);
        int pom;
        int potomki[][] =new int[2][chromosomA.length];
        for(int i =0;i< chromosomA.length;i++)
        {
            if(i<pk) {
                potomki[0][i]= chromosomA[i];
                potomki[1][i]= chromosomB[i];
            }
            else
            {
                potomki[0][i]= chromosomB[i];
                potomki[1][i]= chromosomA[i];
            }
        }
        return potomki;
    }


    public int[][] krzyżowanie2pkt(int[] chromosomA,int[] chromosomB)
    {
        int pk1 = new Random().nextInt(chromosomA.length);
        int pk2 = 0;
        while (pk2<=pk1) {
            pk2 = new Random().nextInt(chromosomA.length);
        }
        int pom;
        int [][] potomki = new int[2][chromosomA.length];
        for(int i =0;i< chromosomA.length;i++)
        {
            if(i<pk1) {
                potomki[0][i]= chromosomA[i];
                potomki[1][i]= chromosomB[i];
            }
            else if(i<pk2)
            {
                potomki[0][i]= chromosomB[i];
                potomki[1][i]= chromosomA[i];
            }
            else
            {
                potomki[0][i]= chromosomA[i];
                potomki[1][i]= chromosomB[i];
            }
        }
        return potomki;
    }

    public int mutation(int[] chromosom, double Pm)
    {
        int n =0;
        Random r = new Random();
        for (int i =0;i<chromosom.length;i++) {
            if(r.nextDouble()<Pm)
            {
                n++;
                if(chromosom[i] == 1)
                {
                    chromosom[i]=0;
                }
                else
                {
                    chromosom[i]=1;
                }
            }
        }
        return n;
    }

    public static void generujPermu(int[] baza)
    {
        Random r = new Random();
        int i1=0,i2=0;
        int pom;
        for(int i = 0; i<100;i++)
        {
            i1=r.nextInt(10);
            i2=r.nextInt(10);

            pom  = baza[i1];
            baza[i1] = baza[i2];
            baza[i2] = pom;
        }
    }

    public static int[][] pmx(int[] rodzicA,int[] rodzicB)
    {
        int [][] potomki = new int[2][rodzicA.length];
        int pk1 = new Random().nextInt(rodzicA.length);
        int pk2 = 0;
        while (pk2<=pk1) {
            pk2 = new Random().nextInt(rodzicA.length);
        }
        for(int i = pk1;i<=pk2;i++)
        {
                potomki[0][i]= rodzicB[i];
                potomki[1][i]= rodzicA[i];
        }

        System.out.print("P1 E1:  ");
        for(int x:potomki[0])
        {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.print("P2 E1:  ");
        for(int x:potomki[1])
        {
            System.out.print(x + " ");
        }
        System.out.println();

        for(int i = 0; i < potomki[0].length; i++)
        {
            if(i==pk1&&pk2<potomki[0].length-1)
            {
                i=pk2+1;
            }
            boolean duplikatA = false;
            boolean duplikatB = false;
           for(int j = pk1;j<=pk2;j++)
           {
               if(rodzicA[i] == potomki[0][j])
               {
                   duplikatA = true;
               }
               if(rodzicB[i] == potomki[1][j])
               {
                   duplikatB = true;
               }
           }
           if(!duplikatA)
           {
               potomki[0][i] = rodzicA[i];
           } if(!duplikatB)
           {
               potomki[1][i] = rodzicB[i];
           }
        }

        System.out.print("P1 E2:  ");
        for(int x:potomki[0])
        {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.print("P2 E2:  ");
        for(int x:potomki[1])
        {
            System.out.print(x + " ");
        }
        System.out.println();

        for(int i = 0; i < potomki[0].length; i++)
        {
            if(potomki[0][i] == 0)
            {
                int p = 1;
                boolean wystepuje = false;
                while(true)
                {
                    for (int x:potomki[0])
                    {
                        if(x==p)
                        {
                            wystepuje = true;
                        }
                    }
                    if(wystepuje)
                    {
                        p++;
                        if(p>10)
                        {
                            break;
                        }
                        wystepuje = false;
                    }
                    else {
                        potomki[0][i] = p;
                        break;
                    }
                }
            }

            if(potomki[1][i] == 0)
            {
                int p = 1;
                boolean wystepuje = false;
                while(true)
                {
                    for (int x:potomki[1])
                    {
                        if(x==p)
                        {
                            wystepuje = true;
                        }
                    }
                    if(wystepuje)
                    {
                        p++;
                        if(p>10)
                        {
                            break;
                        }
                        wystepuje = false;
                    }
                    else {
                        potomki[1][i] = p;
                        break;
                    }
                }
            }
        }

        return potomki;
    }
}
