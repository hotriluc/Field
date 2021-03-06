import jdk.internal.dynalink.beans.StaticClass;
import org.jfree.ui.RefineryUtilities;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
//AES PRN TEST

public class AESSeq {
    private int []arr;

    public int[] genereteSeq(int p){
        //Генерация криптосигналов
        SecureRandom random = new SecureRandom();

            arr = new int[p];

            for (int i = 0; i < arr.length; i++) {

                int a = random.nextInt(2);
                if (a == 0) {
                    arr[i] = -1;
                } else
                    arr[i] = a;
            }
            return this.arr;


    }

    public int[] genereteSeq(int p , int if_rmax){
        while (true) {
            arr = genereteSeq(p);
            System.out.print(Arrays.toString(arr));
            Signal s1 = new Signal(p);
            int[] arr1 = arr.clone(); // will be shifted
            s1.setSignal(arr1);
            System.out.println("\nPFAK");

            List<Integer> pereodic_auto_correl_list = s1.getPereodicCorrelList(arr,true);

            int rmax = StatClass.getRmaxWO(pereodic_auto_correl_list, p);

            System.out.println("\nRmax:" + rmax);
            System.out.println("Count: " + StatClass.getCntAndPos(pereodic_auto_correl_list, rmax));
            if(rmax <=if_rmax){break;}
        }
        return  arr;
    }
    public static  void  main(String args[]){


        List<Integer> apereodic_auto_correl_list ;
        int p;
        int[] arr;
        AESSeq seq = new AESSeq();

        p = new Scanner(System.in).nextInt();
        int if_rmax = new Scanner(System.in).nextInt();
       // p = 130;
        //Генерация криптосигналов


       arr =  seq.genereteSeq(p,if_rmax);

            Signal s2 = new Signal(p);
            int[] arr2 = arr.clone(); // will be shifted
            s2.setSignal(arr2);
            System.out.println("AFAK");

            apereodic_auto_correl_list = s2.getApereodicCorrelList(arr,true);
             apereodic_auto_correl_list.addAll(apereodic_auto_correl_list);
        int rmax_aper =StatClass.getRmaxWO(apereodic_auto_correl_list, p );
        System.out.println("\nRmax="+rmax_aper);
        System.out.println("Count: "+StatClass.getCntAndPos(apereodic_auto_correl_list,rmax_aper));
        Plot chart2 = new Plot("АФАК",
                "АФАК"+", L = "+Integer.toString(p),apereodic_auto_correl_list);
        chart2.pack( );
        RefineryUtilities.centerFrameOnScreen( chart2 );
        chart2.setVisible( true );
    }
}
