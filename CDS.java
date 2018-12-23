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


public class CDS {




    public static void main (String args[]) throws IOException {
        int p=0;
        int teta_min = 2;
        Scanner sc = new Scanner(System.in);
        boolean prime_flag=false;

        do{
            p = sc.nextInt();
            if (StatClass.isPrime(p)) {
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
        StatClass.printArr(b);


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
        int cnt_max = StatClass.getRmaxCnt(pereodic_cross_correl_list);
        int cnt_min = StatClass.getRminCnt(pereodic_cross_correl_list);
        int Rmax = StatClass.getRmax(pereodic_cross_correl_list);
        int Rmin = StatClass.getRmin(pereodic_cross_correl_list);
       System.out.printf("\nRmax: %d , Count: %d",Rmax,cnt_max);
        System.out.printf("\nRmin: %d , Count: %d\n",Rmin,cnt_min);
        double x = Rmax/Math.sqrt(p-1);
        System.out.printf("x = Rmax/\u221AL = %d/\u221A%d = %f\n",Rmax,p-1,x);
        /*SignalCount*/
        System.out.printf("Signal count = %d/2 = %d",dec_list.size(),dec_list.size()/2);
        //==============CHARTS==============
        Plot chart = new Plot("ПФАК",
                "ПФАК"+", L = "+Integer.toString(p-1),pereodic_auto_correl_list);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );

        Plot chart2 = new Plot("АФАК",
                "АФАК"+", L = "+Integer.toString(p-1),apereodic_auto_correl_list);
        chart2.pack( );
        RefineryUtilities.centerFrameOnScreen( chart2 );
        chart2.setVisible( true );

        Plot chart3 = new Plot("ПФВК",
                "ПФВК"+", L = "+Integer.toString(p-1),pereodic_cross_correl_list);
        chart3.pack( );
        RefineryUtilities.centerFrameOnScreen( chart3 );

        chart3.setVisible( true );


        Plot chart4 = new Plot("АФВК",
                "АФВК"+", L = "+Integer.toString(p-1),apereodic_cross_correl_list);
        chart4.pack( );
        RefineryUtilities.centerFrameOnScreen( chart4 );
        chart4.setVisible( true );



     // MainFrame mf = new MainFrame(pereodic_auto_correl_list,apereodic_auto_correl_list,pereodic_cross_correl_list,apereodic_cross_correl_list);

    }
}
