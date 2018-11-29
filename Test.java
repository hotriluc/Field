import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

public class Test {

    static void printArr(int arr[]){
        for(int i:arr){
            System.out.printf("%4d",i);
        }
    }

    public static boolean isPrime(int n) {
       //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    static int getRmax(List<Integer> list){
        return  Collections.max(list);
    }
    static int getRmin(List<Integer> list){
        return  Collections.min(list);
    }

    static int getRmaxCnt(List<Integer> list){

        int cnt_max = 0;
        int max = list.get(0);


        for(int i=0;i<list.size();i++){
            if(list.get(i)==max){
                cnt_max++;
            }else if(list.get(i)>max){
                max = list.get(i);
                cnt_max = 1;
            }
        }
        return cnt_max;
    }

    static int getRminCnt(List<Integer> list){

        int cnt_min = 0;
        int min = list.get(0);

        for(int i=0;i<list.size();i++){
            if(list.get(i)==min){
                cnt_min++;
            }else if(list.get(i)<min){
                min = list.get(i);
                cnt_min = 1;
            }
        }
        return cnt_min;
    }


    public static void main (String args[]) throws IOException {
        int p=0;
        int teta_min = 2;
        Scanner sc = new Scanner(System.in);
        boolean prime_flag=false;

        do{
            p = sc.nextInt();
            if (isPrime(p)) {
                System.out.println("prime");
                break;
            } else {
                System.out.println("no prime");
            }
        }while (prime_flag!=true);



        Field f1 = new Field(p);
        System.out.println("P = "+p+"\nL = "+(p-1));


        //euler
        Euler eil = new Euler(p-1);
        System.out.println("\u03D5("+(p-1)+") = "+eil.getPhi());

        //with euclide algorithm searching for all coprime of n
         ArrayList<Integer> cl = eil.getCoprime();
        eil.printCoprime();

        /*
        * TETA_MIN CALCULATING
        *
        *
        * */
        Euler eil2 = new Euler(p);
        System.out.print("\u03D5("+(p)+") = "+eil2.getPhi()+ " = ");
        teta_min = f1.getTeta_min(eil2);

        System.out.printf("For P =%d \u03F4min = %d",p,teta_min);



       // f1.getTetaMin(2,cl);
        /*Getting All Tetas*/
      /*  System.out.println("Let \u03F4min = "+teta_min+" then");
        f1.getTetaList(teta_min,cl);
        f1.printTetaList();*/

        /*Setting Teta*/
        f1.setTeta(teta_min);
        System.out.printf("\n\u03F4 = %d\n",f1.getTeta());

        /*FillingTable*/
        f1.fill_row_Ui();
        f1.fill_row_ai();
        f1.fill_row_Ai();
        f1.fill_row_bi();
        f1.fill_row_MH();
        f1.fill_row_Psi();
        f1.printArray();


//=======================PEREODICAUTOCORRELATIONFUNCTION==================================
        int[]b = f1.getPsi();//source signal
        int[]b1 ;//creating same signal which will be shifted
        int[]b2;
        b1 = b.clone();
        b2 = b.clone();
        Signal s1 = new Signal(p);
        s1.setSignal(b1);
        System.out.println("PFAK");

         List<Integer> pereodic_auto_correl_list=s1.getAutoCorrelList(b);
        //s1.printCorrelList();


        System.out.println("\nAFAK");
        Signal s2 = new Signal(p);
        s2.setSignal(b2);
        List<Integer> apereodic_auto_correl_list = s2.getApereodicCorrelList(b);





//=========================================================






        System.out.println();
        System.out.println("Source Signal");
        printArr(b);


        List<int[]> dec_list =  new ArrayList<>();
        try (BufferedWriter  bf = new BufferedWriter(new FileWriter("Decimation.txt"))) {

            for (int i = 0; i < cl.size(); i++) {
                int tmp_arr1 [] = new int[p-1];
                //System.out.printf("\nDecimation Coef = %d\n", cl.get(i));
                s1.decimation(b, tmp_arr1, cl.get(i));
                //printArr(tmp_arr1);
                dec_list.add(tmp_arr1);
                bf.write(Arrays.toString(tmp_arr1)+"\n");
                bf.flush();
            }

        }

        System.out.println("\n");
        int[] dec_signal = dec_list.get(2);
        int[] b3 = dec_signal.clone();
        int[] b4 = dec_signal.clone();

        Signal s3 = new Signal(p);
        s3.setSignal(b3);
        Signal s4 = new Signal(p);
        s4.setSignal(b4);
        for(int i:dec_signal){
            System.out.printf("%4d",i);
        }
        System.out.println("\nPFVK");
        List<Integer> pereodic_cross_correl_list=s3.getAutoCorrelList(b);
        System.out.println("\nAFVK");
        List<Integer> apereodic_cross_correl_list=s4.getApereodicCorrelList(b);

        /*Getting Rmax and Rmin*/
        int cnt_max = getRmaxCnt(pereodic_cross_correl_list);
        int cnt_min = getRminCnt(pereodic_cross_correl_list);
        int Rmax = getRmax(pereodic_cross_correl_list);
        int Rmin = getRmin(pereodic_cross_correl_list);
       System.out.printf("\nRmax: %d , Count: %d",Rmax,cnt_max);
        System.out.printf("\nRmin: %d , Count: %d\n",Rmin,cnt_min);
        double x = Rmax/Math.sqrt(p-1);
        System.out.printf("x = Rmax/\u221AL = %d/\u221A%d = %f\n",Rmax,p-1,x);
        /*SignalCount*/
        System.out.printf("Signal count = %d/2 = %d",dec_list.size(),dec_list.size()/2);
        //==============CHARTS==============
      /*  Plot chart = new Plot("ПФАК",
                "ПФАК",pereodic_auto_correl_list);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );

        Plot chart2 = new Plot("АФАК",
                "АФАК",apereodic_auto_correl_list);
        chart2.pack( );
        RefineryUtilities.centerFrameOnScreen( chart2 );
        chart2.setVisible( true );

        Plot chart3 = new Plot("ПФВК",
                "ПФВК",pereodic_cross_correl_list);
        chart3.pack( );
        RefineryUtilities.centerFrameOnScreen( chart3 );

        chart3.setVisible( true );


        Plot chart4 = new Plot("АФВК",
                "АФВК",apereodic_cross_correl_list);
        chart4.pack( );
        RefineryUtilities.centerFrameOnScreen( chart4 );
        chart4.setVisible( true );

        */

      MainFrame mf = new MainFrame(pereodic_auto_correl_list,apereodic_auto_correl_list,pereodic_cross_correl_list,apereodic_cross_correl_list);

    }
}
