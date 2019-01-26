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

    public static  void  main(String args[]){
        SecureRandom random = new SecureRandom();

        List<Integer> apereodic_auto_correl_list ;
        int p;
        int[] arr;
        p = new Scanner(System.in).nextInt();


            arr = new int[p];

            for (int i = 0; i < arr.length; i++) {

                int a = random.nextInt(2);
                if (a == 0) {
                    arr[i] = -1;
                } else
                    arr[i] = a;
            }
            //Source Signal arr[]
            System.out.print(Arrays.toString(arr));
            Signal s1 = new Signal(p);
            int[] arr1 = arr.clone(); // will be shifted
            s1.setSignal(arr1);
            System.out.println("PFAK");

            List<Integer> pereodic_auto_correl_list = s1.getAutoCorrelList(arr);
            System.out.println(StatClass.getRmaxWO(pereodic_auto_correl_list,p));
        System.out.println(StatClass.getRmax(pereodic_auto_correl_list)+" "+StatClass.getRmaxCnt(pereodic_auto_correl_list));

           /* Signal s2 = new Signal(p);
            int[] arr2 = arr.clone(); // will be shifted
            s2.setSignal(arr2);
            System.out.println("AFAK");

            apereodic_auto_correl_list = s2.getApereodicCorrelList(arr);
             apereodic_auto_correl_list.addAll(apereodic_auto_correl_list);

        Plot chart2 = new Plot("АФАК",
                "АФАК"+", L = "+Integer.toString(p-1),apereodic_auto_correl_list);
        chart2.pack( );
        RefineryUtilities.centerFrameOnScreen( chart2 );
        chart2.setVisible( true );*/
    }
}
