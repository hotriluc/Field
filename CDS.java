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

enum FVK{
    PFVK,
    AFVK,
    PFAK,
    AFAK
}

public class CDS {
    private Field f1;
    private List<int[]> decimation_list;//ХДС ДЛЯ РАЗНЫХ КОЕФ ДЕЦИМАЦИИ

    public static void SourceSigWithSigList(FVK f,int[]source_sig,List<int[]> sig_list,boolean printflag) {
        switch (f) {
            case PFVK: {
                System.out.println("\n========================");
                List<Integer> rmax_list = new ArrayList<>();
                List<Double> avg_module_list = new ArrayList<>();
                List<Double> dispersion_module_list = new ArrayList<>();
                List<Double> deviation_module_list = new ArrayList<>();

                List<Double> avg_list = new ArrayList<>();
                List<Double> dispersion_list = new ArrayList<>();
                List<Double> deviation_list = new ArrayList<>();


                /*для расчета фвк исходного сигнала с остальными сигналами коеф децимации
                 * цикл начинаем с 1(т.е без сигнала построенного коеф децимицаии 1)
                 * */
                for (int i = 1; i < sig_list.size(); i++) {
                    Signal s = new Signal();
                    int[]tmp = sig_list.get(i).clone();
                    s.setSignal(tmp);
                    System.out.println("\nPFVK");
                    List<Integer> pereodic_cross_correl_list = s.getPereodicCorrelList(source_sig, printflag);

                    int r_max = StatClass.getRmaxWO(pereodic_cross_correl_list, source_sig.length);
                    System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, source_sig.length) + "\u221A" + source_sig.length);
                    System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list, r_max));

                    /*
                    int r_min = StatClass.getRmin(pereodic_cross_correl_list);
                    System.out.printf("Rmin: %d \n",r_min);
                    System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list,r_min));
                    */

                    //для значения rmax
                    rmax_list.add(r_max);

                    /*
                      Для модулей
                    //для мат ожидание модулей боковых выбросов
                    List<Integer> tmp_list = new ArrayList<>();
                    tmp_list.addAll(pereodic_cross_correl_list);
                    StatClass.ModuleSignal(tmp_list);
                    double avg_module_x = StatClass.CalculateAVG(tmp_list);
                    System.out.println("AVG_module:" + avg_module_x / Math.sqrt(source_sig.length));
                    avg_module_list.add(StatClass.CalculateAVG(tmp_list));

                    //дисперсия модулей
                    double dispersion_module_x = StatClass.CalculateDispersion(tmp_list, avg_module_x);
                    System.out.println("Dispersion_Module:" + dispersion_module_x / Math.sqrt(source_sig.length));
                    dispersion_module_list.add(dispersion_module_x);

                    //СКО модулей
                    double deviation_module_x = Math.sqrt(dispersion_module_x);
                    System.out.println("Deviation_Module:" + deviation_module_x / Math.sqrt(source_sig.length));
                    deviation_module_list.add(deviation_module_x);
                    */

                    //FLAG = TRUE для модулей боковых выбросов
                    StatClass.CalculateAndSet(pereodic_cross_correl_list,source_sig,avg_module_list,dispersion_module_list,deviation_module_list,true);


                /*Для всех боковых выбросов*/
                    /*
                    List<Integer> tmp_list2 = new ArrayList<>();
                    tmp_list2.addAll(pereodic_cross_correl_list);

                    double avg_x = StatClass.CalculateAVG(tmp_list2);
                    System.out.println("AVG_module:" + avg_x / Math.sqrt(source_sig.length));
                    avg_list.add(StatClass.CalculateAVG(tmp_list2));

                    double dispersion_x = StatClass.CalculateDispersion(tmp_list2, avg_x);
                    System.out.println("Dispersion_Module:" + dispersion_x / Math.sqrt(source_sig.length));
                    dispersion_list.add(dispersion_x);

                    double deviation_x = Math.sqrt(dispersion_x);
                    System.out.println("Deviation_Module:" + deviation_x / Math.sqrt(source_sig.length));
                    deviation_list.add(deviation_x);*/

                    /*flag = false Для всех боковых выбросов*/
                    StatClass.CalculateAndSet(pereodic_cross_correl_list,source_sig,avg_list,dispersion_list,deviation_list,false);

                }

                System.out.println("\n==========STAT==========");
                System.out.println("X = " + StatClass.CalculateAVG(rmax_list) / Math.sqrt(source_sig.length));
                System.out.println("AVG_Module = " + StatClass.CalculateAVG(avg_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_Module = " + StatClass.CalculateAVG(dispersion_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_Module = " + StatClass.CalculateAVG(deviation_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("AVG_ = " + StatClass.CalculateAVG(avg_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_ = " + StatClass.CalculateAVG(dispersion_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_ = " + StatClass.CalculateAVG(deviation_list)/ Math.sqrt(source_sig.length));
                System.out.println("\n========================");
            }
            break;

            case AFVK: {

                List<Integer> rmax_list = new ArrayList<>();
                List<Double> avg_module_list = new ArrayList<>();
                List<Double> dispersion_module_list = new ArrayList<>();
                List<Double> deviation_module_list = new ArrayList<>();

                List<Double> avg_list = new ArrayList<>();
                List<Double> dispersion_list = new ArrayList<>();
                List<Double> deviation_list = new ArrayList<>();
                /*для расчета фвк исходного сигнала с остальными сигналами коеф децимации
                 * цикл начинаем с 1(т.е без сигнала построенного коеф децимицаии 1)
                 * */

                for (int i = 1; i < sig_list.size(); i++) {
                    Signal s2 = new Signal();
                    int[]tmp = sig_list.get(i).clone();
                    s2.setSignal(tmp);

                    System.out.println("\nAFVK");
                    List<Integer> apereodic_cross_correl_list = new ArrayList<>();
                    apereodic_cross_correl_list = s2.getApereodicCorrelList(source_sig, printflag);

                    int r_max = StatClass.getRmaxWO(apereodic_cross_correl_list, source_sig.length);
                    System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, source_sig.length) + "\u221A" + source_sig.length);
                    System.out.println("Count: " + StatClass.getCntAndPos(apereodic_cross_correl_list, r_max));

                    /*
                    int r_min = StatClass.getRmin(pereodic_cross_correl_list);
                    System.out.printf("Rmin: %d \n",r_min);
                    System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list,r_min));
                    */
                    //для значения rmax
                    rmax_list.add(r_max);

                    StatClass.CalculateAndSet(apereodic_cross_correl_list,source_sig,avg_module_list,dispersion_module_list,deviation_module_list,true);
                    StatClass.CalculateAndSet(apereodic_cross_correl_list,source_sig,avg_list,dispersion_list,deviation_list,false);



                }

                System.out.println("\n==========STAT==========");
                System.out.println("X = " + StatClass.CalculateAVG(rmax_list) / Math.sqrt(source_sig.length));
                System.out.println("AVG_Module = " + StatClass.CalculateAVG(avg_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_Module = " + StatClass.CalculateAVG(dispersion_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_Module = " + StatClass.CalculateAVG(deviation_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("AVG_ = " + StatClass.CalculateAVG(avg_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_ = " + StatClass.CalculateAVG(dispersion_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_ = " + StatClass.CalculateAVG(deviation_list)/ Math.sqrt(source_sig.length));
                System.out.println("\n========================");
            }break;


            case PFAK:{
                System.out.println("\n========================");
                List<Integer> rmax_list = new ArrayList<>();
                List<Double> avg_module_list = new ArrayList<>();
                List<Double> dispersion_module_list = new ArrayList<>();
                List<Double> deviation_module_list = new ArrayList<>();

                List<Double> avg_list = new ArrayList<>();
                List<Double> dispersion_list = new ArrayList<>();
                List<Double> deviation_list = new ArrayList<>();
                for (int i = 1; i < sig_list.size(); i++) {
                    Signal s = new Signal();
                    int[] tmp_source_sig = sig_list.get(i).clone();
                    int[] tmp_shifted_sig = sig_list.get(i).clone();
                    s.setSignal(tmp_shifted_sig);
                    System.out.println("\nPFAK");
                    List<Integer> pereodic_auto_correl_list = s.getPereodicCorrelList(tmp_source_sig, printflag);

                    /*Без учета первого и последего*/
                    int r_max = StatClass.getRmaxWO(pereodic_auto_correl_list, source_sig.length);
                    pereodic_auto_correl_list.remove(0);
                    pereodic_auto_correl_list.remove(pereodic_auto_correl_list.size()-1);


                    System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, source_sig.length) + "\u221A" + source_sig.length);
                    System.out.println("Count: " + StatClass.getCntAndPos(pereodic_auto_correl_list, r_max));



                    //для значения rmax
                    rmax_list.add(r_max);


                    StatClass.CalculateAndSet(pereodic_auto_correl_list,source_sig,avg_module_list,dispersion_module_list,deviation_module_list,true);



                    StatClass.CalculateAndSet(pereodic_auto_correl_list,source_sig,avg_list,dispersion_list,deviation_list,false);

                }

                System.out.println("\n==========STAT==========");
                System.out.println("X = " + StatClass.CalculateAVG(rmax_list) / Math.sqrt(source_sig.length));
                System.out.println("AVG_Module = " + StatClass.CalculateAVG(avg_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_Module = " + StatClass.CalculateAVG(dispersion_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_Module = " + StatClass.CalculateAVG(deviation_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("AVG_ = " + StatClass.CalculateAVG(avg_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_ = " + StatClass.CalculateAVG(dispersion_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_ = " + StatClass.CalculateAVG(deviation_list)/ Math.sqrt(source_sig.length));
                System.out.println("\n========================");
            }
            break;


            default: {
                System.out.println("No such a method");
            }
            break;

        }


    }

    public  void SourceSigWithSigList(FVK f,boolean printflag) {
        int[]source_sig = f1.getPsi();
        switch (f) {
            case PFVK: {
                System.out.println("\n========================");
                List<Integer> rmax_list = new ArrayList<>();
                List<Double> avg_module_list = new ArrayList<>();
                List<Double> dispersion_module_list = new ArrayList<>();
                List<Double> deviation_module_list = new ArrayList<>();

                List<Double> avg_list = new ArrayList<>();
                List<Double> dispersion_list = new ArrayList<>();
                List<Double> deviation_list = new ArrayList<>();


                /*для расчета фвк исходного сигнала с остальными сигналами коеф децимации
                 * цикл начинаем с 1(т.е без сигнала построенного коеф децимицаии 1)
                 * */
                for (int i = 1; i < this.decimation_list.size(); i++) {
                    Signal s = new Signal();
                    s.setSignal(decimation_list.get(i));
                    System.out.println("\nPFVK");
                    List<Integer> pereodic_cross_correl_list = s.getPereodicCorrelList(source_sig, printflag);

                    int r_max = StatClass.getRmaxWO(pereodic_cross_correl_list, source_sig.length);
                    System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, source_sig.length) + "\u221A" + source_sig.length);
                    System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list, r_max));

                    /*
                    int r_min = StatClass.getRmin(pereodic_cross_correl_list);
                    System.out.printf("Rmin: %d \n",r_min);
                    System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list,r_min));
                    */

                    //для значения rmax
                    rmax_list.add(r_max);

                    /*
                      Для модулей
                    //для мат ожидание модулей боковых выбросов
                    List<Integer> tmp_list = new ArrayList<>();
                    tmp_list.addAll(pereodic_cross_correl_list);
                    StatClass.ModuleSignal(tmp_list);
                    double avg_module_x = StatClass.CalculateAVG(tmp_list);
                    System.out.println("AVG_module:" + avg_module_x / Math.sqrt(source_sig.length));
                    avg_module_list.add(StatClass.CalculateAVG(tmp_list));

                    //дисперсия модулей
                    double dispersion_module_x = StatClass.CalculateDispersion(tmp_list, avg_module_x);
                    System.out.println("Dispersion_Module:" + dispersion_module_x / Math.sqrt(source_sig.length));
                    dispersion_module_list.add(dispersion_module_x);

                    //СКО модулей
                    double deviation_module_x = Math.sqrt(dispersion_module_x);
                    System.out.println("Deviation_Module:" + deviation_module_x / Math.sqrt(source_sig.length));
                    deviation_module_list.add(deviation_module_x);
                    */

                    //FLAG = TRUE для модулей боковых выбросов
                    StatClass.CalculateAndSet(pereodic_cross_correl_list,source_sig,avg_module_list,dispersion_module_list,deviation_module_list,true);


                    /*Для всех боковых выбросов*/
                    /*
                    List<Integer> tmp_list2 = new ArrayList<>();
                    tmp_list2.addAll(pereodic_cross_correl_list);

                    double avg_x = StatClass.CalculateAVG(tmp_list2);
                    System.out.println("AVG_module:" + avg_x / Math.sqrt(source_sig.length));
                    avg_list.add(StatClass.CalculateAVG(tmp_list2));

                    double dispersion_x = StatClass.CalculateDispersion(tmp_list2, avg_x);
                    System.out.println("Dispersion_Module:" + dispersion_x / Math.sqrt(source_sig.length));
                    dispersion_list.add(dispersion_x);

                    double deviation_x = Math.sqrt(dispersion_x);
                    System.out.println("Deviation_Module:" + deviation_x / Math.sqrt(source_sig.length));
                    deviation_list.add(deviation_x);*/

                    /*flag = false Для всех боковых выбросов*/
                    StatClass.CalculateAndSet(pereodic_cross_correl_list,source_sig,avg_list,dispersion_list,deviation_list,false);

                }

                System.out.println("\n==========STAT==========");
                System.out.println("X = " + StatClass.CalculateAVG(rmax_list) / Math.sqrt(source_sig.length));
                System.out.println("AVG_Module = " + StatClass.CalculateAVG(avg_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_Module = " + StatClass.CalculateAVG(dispersion_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_Module = " + StatClass.CalculateAVG(deviation_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("AVG_ = " + StatClass.CalculateAVG(avg_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_ = " + StatClass.CalculateAVG(dispersion_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_ = " + StatClass.CalculateAVG(deviation_list)/ Math.sqrt(source_sig.length));
                System.out.println("\n========================");
            }
            break;

            case AFVK: {

                List<Integer> rmax_list = new ArrayList<>();
                List<Double> avg_module_list = new ArrayList<>();
                List<Double> dispersion_module_list = new ArrayList<>();
                List<Double> deviation_module_list = new ArrayList<>();

                List<Double> avg_list = new ArrayList<>();
                List<Double> dispersion_list = new ArrayList<>();
                List<Double> deviation_list = new ArrayList<>();
                /*для расчета фвк исходного сигнала с остальными сигналами коеф децимации
                 * цикл начинаем с 1(т.е без сигнала построенного коеф децимицаии 1)
                 * */
                for (int i = 1; i < decimation_list.size(); i++) {
                    Signal s = new Signal();
                    s.setSignal(decimation_list.get(i));
                    System.out.println("\nAFVK");
                    List<Integer> apereodic_cross_correl_list = s.getApereodicCorrelList(source_sig, printflag);

                    int r_max = StatClass.getRmaxWO(apereodic_cross_correl_list, source_sig.length);
                    System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, source_sig.length) + "\u221A" + source_sig.length);
                    System.out.println("Count: " + StatClass.getCntAndPos(apereodic_cross_correl_list, r_max));

                    /*
                    int r_min = StatClass.getRmin(pereodic_cross_correl_list);
                    System.out.printf("Rmin: %d \n",r_min);
                    System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list,r_min));
                    */
                    //для значения rmax
                    rmax_list.add(r_max);

                    StatClass.CalculateAndSet(apereodic_cross_correl_list,source_sig,avg_module_list,dispersion_module_list,deviation_module_list,true);
                    StatClass.CalculateAndSet(apereodic_cross_correl_list,source_sig,avg_list,dispersion_list,deviation_list,false);



                }

                System.out.println("\n==========STAT==========");
                System.out.println("X = " + StatClass.CalculateAVG(rmax_list) / Math.sqrt(source_sig.length));
                System.out.println("AVG_Module = " + StatClass.CalculateAVG(avg_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_Module = " + StatClass.CalculateAVG(dispersion_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_Module = " + StatClass.CalculateAVG(deviation_module_list)/ Math.sqrt(source_sig.length));
                System.out.println("AVG_ = " + StatClass.CalculateAVG(avg_list)/ Math.sqrt(source_sig.length));
                System.out.println("Dispersion_ = " + StatClass.CalculateAVG(dispersion_list)/ Math.sqrt(source_sig.length));
                System.out.println("Deviation_ = " + StatClass.CalculateAVG(deviation_list)/ Math.sqrt(source_sig.length));
                System.out.println("\n========================");
            }break;

            default: {
                System.out.println("No such a method");
            }
            break;

        }


    }

    public List<Integer> getSelectedDecimationCoef(int N){
        List<Integer> selected_decimation_coef = new ArrayList<>();
        for(int i =1;i<decimation_list.size();i++) {
            //System.out.println("\nAFAK");

            int[] dec_signal = decimation_list.get(i);
            int[] b3 = dec_signal.clone();
            Signal s3 = new Signal();
            s3.setSignal(b3);
            // s3.printSignal();

            // System.out.println(Arrays.toString(dec_signal));
            List<Integer> apereodic_auto_correl_list2 = s3.getApereodicCorrelList(dec_signal,false);

            //System.out.println("\n"+i+"\nDecimation coef:"+f1.getEuler().getCoprime().get(i));
            int rmax =StatClass.getRmaxWO(apereodic_auto_correl_list2, decimation_list.get(i).length);
            //System.out.println("Rmax="+rmax);
            if(rmax == N){
                //System.out.println(f1.getEuler().getCoprime().get(i)+" поз "+i);
                selected_decimation_coef.add(this.f1.getEuler().getCoprime().get(i));
            }

        }
        return selected_decimation_coef;
    }

    public void decimation(int []arr, int []arr2, int d){
        for (int i=0;i<arr.length;i++){
            int pos = ((d+d*i))% arr.length;
            arr2[i]=arr[pos];
        }
    }

    public List<int[]> getDecimation_list(int[]source_signal,List<Integer> coprime_list) throws IOException{
        int p = source_signal.length;
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("Decimation.txt"))) {

            for (int i = 0; i < coprime_list.size(); i++) {
                int tmp_arr1 [] = new int[p];
                //System.out.printf("\nDecimation Coef = %d\n", cl.get(i));
                decimation(source_signal, tmp_arr1, coprime_list.get(i));
                //printArr(tmp_arr1);
                decimation_list.add(tmp_arr1);
                bf.write(Arrays.toString(tmp_arr1)+"\n");
                bf.flush();
            }

        }
        return this.decimation_list;
    }

    public List<int[]> getDecimation_list(){
        return this.decimation_list;
    }

    public CDS()throws IOException{
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



        this.f1 = new Field(p);
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
        /*
        int[]b1 ;//creating same signal which will be shifted
        int[]b2;
        b1 = b.clone();
        b2 = b.clone();
        Signal s1 = new Signal(p);
        s1.setSignal(b1);
        System.out.println("PFAK");

        List<Integer> pereodic_auto_correl_list=s1.getPereodicCorrelList(b,true);
        //s1.printCorrelList();


        System.out.println("\nAFAK");
        Signal s2 = new Signal(p);
        s2.setSignal(b2);
        List<Integer> apereodic_auto_correl_list = s2.getApereodicCorrelList(b,true);
        */
        decimation_list = new ArrayList<>();
        getDecimation_list(b,f1.getEuler().getCoprime());

    }

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

        List<Integer> pereodic_auto_correl_list=s1.getPereodicCorrelList(b,true);
        //s1.printCorrelList();


        System.out.println("\nAFAK");
        Signal s2 = new Signal(p);
        s2.setSignal(b2);
        List<Integer> apereodic_auto_correl_list = s2.getApereodicCorrelList(b,true);





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


        /*ТУТ ВЫБИРАЕМ СИГНАЛ  ИЗ СПИСКА СИГНАЛОВ ПОСТРОЕНЫХ
         * ПУТЕМ ДЕЦИМАЦИИ ,КОТОРЫй БУДЕМ СКЛАДЫВАТЬ С ИСХОДНЫМ СИГНАЛОМ*/
        System.out.println("\n");
        int[] dec_signal = dec_list.get(dec_list.size()-1);
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
        List<Integer> pereodic_cross_correl_list=s3.getPereodicCorrelList(b,true);

        System.out.println("\nAFVK");
        List<Integer> apereodic_cross_correl_list=s4.getApereodicCorrelList(b,true);

        /*Getting Rmax and Rmin for PFVK*/

        int Rmax = StatClass.getRmax(pereodic_cross_correl_list);
        System.out.printf("\nRmax: %d ",Rmax);
        int cnt_max = StatClass.getCntAndPos(pereodic_cross_correl_list,Rmax);


        int Rmin = StatClass.getRmin(pereodic_cross_correl_list);
        System.out.printf("\nRmin: %d ",Rmin);
        int cnt_min = StatClass.getCntAndPos(pereodic_cross_correl_list,Rmin);


        // System.out.printf("\nRmin: %d , Count: %d\n",Rmin,cnt_min);
        double x = Rmax/Math.sqrt(p-1);
        System.out.printf("\nx = Rmax/\u221AL = %d/\u221A%d = %f\n",Rmax,p-1,x);
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

        CDS.SourceSigWithSigList(FVK.PFVK,b,dec_list,false);

        CDS.SourceSigWithSigList(FVK.AFVK,b,dec_list,false);

        CDS.SourceSigWithSigList(FVK.PFAK,b,dec_list,true);


        // MainFrame mf = new MainFrame(pereodic_auto_correl_list,apereodic_auto_correl_list,pereodic_cross_correl_list,apereodic_cross_correl_list);

    }
}