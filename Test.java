import org.jfree.ui.RefineryUtilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[]args)throws IOException {
        int p=0;
        int teta_min = 2;
        Scanner sc = new Scanner(System.in);
       // boolean prime_flag=false;

        do{
            p = new Scanner(System.in).nextInt();
        }while(!StatClass.checkP(p));



        Field f1 = new Field(p);
        f1.CalculateANDprintAllInfo();

        int[]b = f1.getPsi();//source signal
        int[]b1 ;//creating same signal which will be shifted
        int[]b2;
        b1 = b.clone();
        b2 = b.clone();
        Signal s1 = new Signal(p);
        s1.setSignal(b1);
        System.out.println("PFAK");
        s1.printSignal();
        List<Integer> pereodic_auto_correl_list=s1.getPereodicCorrelList(b,true);
        System.out.println(StatClass.getRmaxWO(pereodic_auto_correl_list,p-1));
        System.out.println("\nAFAK");
        Signal s2 = new Signal(p);
        s2.setSignal(b2);
        s2.printSignal();
        List<Integer> apereodic_auto_correl_list = s2.getApereodicCorrelList(b,true);
        System.out.println("\nRmax="+StatClass.getRmaxWO(apereodic_auto_correl_list,p-1));

        List<int[]> dec_list =  new ArrayList<>();
        System.out.println(f1.getEuler().getCoprime().size());
        dec_list = s1.getDecimation_list(b,f1.getEuler().getCoprime());
        /*try (BufferedWriter bf = new BufferedWriter(new FileWriter("Decimation.txt"))) {

            for (int i = 0; i < cl.size(); i++) {
                int tmp_arr1 [] = new int[p-1];
                //System.out.printf("\nDecimation Coef = %d\n", cl.get(i));
                s1.decimation(b, tmp_arr1, cl.get(i));
                //printArr(tmp_arr1);
                dec_list.add(tmp_arr1);
                bf.write(Arrays.toString(tmp_arr1)+"\n");
                bf.flush();
            }

        }*/

    //ОСТАНОВИЛСЯ ВОТ ТУТ КОЕФ ДЕСИМАЦИИ АФАК
        for(int i =1;i<dec_list.size();i++) {
            System.out.println("\nAFAK");

            int[] dec_signal = dec_list.get(i);
            int[] b3 = dec_signal.clone();
            Signal s3 = new Signal(p);
            s3.setSignal(b3);
           // s3.printSignal();

            System.out.println(Arrays.toString(dec_signal));
            List<Integer> apereodic_auto_correl_list2 = s3.getApereodicCorrelList(dec_signal,true);

            System.out.println("\n"+i+"\nDecimation coef:"+f1.getEuler().getCoprime().get(i));
            int rmax =StatClass.getRmaxWO(apereodic_auto_correl_list2, p - 1);
            System.out.println("Rmax="+rmax);
            System.out.println("Count: "+StatClass.getCntAndPos(apereodic_auto_correl_list2,rmax));

        }



    }
}
