import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CryptoSignal {



    public void generateCryptoSignals(List<int[]> alist,int p,int n,int if_rmax){
        while (alist.size()!=n){
            int []source_sig = new AESSeq().genereteSeq(p);

            Signal s = new Signal(p);
            int[] tmp_arr = source_sig.clone(); // will be shifted
            s.setSignal(tmp_arr);


            List<Integer> apereodic_auto_correl_list = s.getApereodicCorrelList(source_sig,false);
            int rmax = StatClass.getRmaxWO(apereodic_auto_correl_list, p);
            int rmin = StatClass.getRmin(apereodic_auto_correl_list);

            if(rmax==if_rmax){
                alist.add(source_sig);
            }


        }


    }

    public static void CryptoSignalStatistics(FVK f,List<int[]> sig_list,boolean printflag){

        List<Integer> rmax_list = new ArrayList<>();
        List<Double> avg_module_list = new ArrayList<>();
        List<Double> dispersion_module_list = new ArrayList<>();
        List<Double> deviation_module_list = new ArrayList<>();

        List<Double> avg_list = new ArrayList<>();
        List<Double> dispersion_list = new ArrayList<>();
        List<Double> deviation_list = new ArrayList<>();
            int p=0;
        switch(f){

            case PFAK:{
                for (int i = 0; i < sig_list.size(); i++) {
                    Signal s = new Signal();
                    int[] tmp_source_sig = sig_list.get(i).clone();
                    int[] tmp_shifted_sig = sig_list.get(i).clone();
                        p = tmp_source_sig.length;
                    s.setSignal(tmp_shifted_sig);
                    System.out.println("\nPFAK");
                    System.out.printf("КС №%d\n",i);
                    System.out.println(Arrays.toString(tmp_shifted_sig));
                    List<Integer> pereodic_auto_correl_list = s.getPereodicCorrelList(tmp_source_sig, printflag);

                    /*Без учета первого и последего*/
                    int r_max = StatClass.getRmaxWO(pereodic_auto_correl_list, tmp_source_sig.length);
                    pereodic_auto_correl_list.remove(0);
                    pereodic_auto_correl_list.remove(pereodic_auto_correl_list.size()-1);


                    System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, tmp_source_sig.length) + "\u221A" + tmp_source_sig.length);
                    System.out.println("Count: " + StatClass.getCntAndPos(pereodic_auto_correl_list, r_max));



                    //для значения rmax
                    rmax_list.add(r_max);


                    StatClass.CalculateAndSet(pereodic_auto_correl_list,tmp_source_sig,avg_module_list,dispersion_module_list,deviation_module_list,true);



                    StatClass.CalculateAndSet(pereodic_auto_correl_list,tmp_source_sig,avg_list,dispersion_list,deviation_list,false);

                }
            }break;

            case AFAK:{
                for (int i = 0; i < sig_list.size(); i++) {
                    Signal s = new Signal();
                    int[] tmp_source_sig = sig_list.get(i).clone();
                    int[] tmp_shifted_sig = sig_list.get(i).clone();
                    p = tmp_source_sig.length;
                    s.setSignal(tmp_shifted_sig);
                    System.out.println("\nAFAK");
                    System.out.printf("КС №%d\n",i);
                    System.out.println(Arrays.toString(tmp_shifted_sig));
                    List<Integer> pereodic_auto_correl_list = s.getApereodicCorrelList(tmp_source_sig, printflag);

                    /*Без учета первого и последего*/
                    int r_max = StatClass.getRmaxWO(pereodic_auto_correl_list, tmp_source_sig.length);
                    pereodic_auto_correl_list.remove(0);
                    pereodic_auto_correl_list.remove(pereodic_auto_correl_list.size()-1);


                    System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, tmp_source_sig.length) + "\u221A" + tmp_source_sig.length);
                    System.out.println("Count: " + StatClass.getCntAndPos(pereodic_auto_correl_list, r_max));



                    //для значения rmax
                    rmax_list.add(r_max);


                    Plot chart2 = new Plot("АФАК",
                            "АФАК"+", КС №"+(i)+", L = "+(p),pereodic_auto_correl_list);
                    chart2.pack( );
                    RefineryUtilities.centerFrameOnScreen( chart2 );
                    chart2.setVisible( true );

                    StatClass.CalculateAndSet(pereodic_auto_correl_list,tmp_source_sig,avg_module_list,dispersion_module_list,deviation_module_list,true);



                    StatClass.CalculateAndSet(pereodic_auto_correl_list,tmp_source_sig,avg_list,dispersion_list,deviation_list,false);

                }
            }break;

            case PFVK:{
                List<Integer> pereodic_cross_correl_list = new ArrayList<>();

                for (int i = 0; i < sig_list.size(); i++) {
                    int[] tmp_source_sig = sig_list.get(i).clone();
                    p = tmp_source_sig.length;
                   for(int j=0;j<sig_list.size();j++)
                        if(i<j) {
                            Signal s = new Signal();
                            int[] tmp = sig_list.get(j).clone();
                            s.setSignal(tmp);
                            System.out.println("\nPFVK");
                            System.out.printf("КС №%d с КС №%d\n",i,j);
                           // System.out.println(Arrays.toString(tmp_source_sig));
                          //  System.out.println(Arrays.toString(tmp));
                            pereodic_cross_correl_list = s.getPereodicCorrelList(tmp_source_sig, printflag);

                            int r_max = StatClass.getRmaxWO(pereodic_cross_correl_list, tmp_source_sig.length);
                            System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, tmp_source_sig.length) + "\u221A" + tmp_source_sig.length);
                            System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list, r_max));


                            rmax_list.add(r_max);

                            Plot chart = new Plot("ПФВК",
                                    "ПФВК"+", КС №"+i+", КС №"+j+", L = "+(p),pereodic_cross_correl_list);
                            chart.pack( );
                            RefineryUtilities.centerFrameOnScreen( chart );
                            chart.setVisible( true );

                            StatClass.CalculateAndSet(pereodic_cross_correl_list,tmp_source_sig,avg_module_list,dispersion_module_list,deviation_module_list,true);



                            StatClass.CalculateAndSet(pereodic_cross_correl_list,tmp_source_sig,avg_list,dispersion_list,deviation_list,false);


                        }





                }
            }break;

            case AFVK:{
                List<Integer> pereodic_cross_correl_list = new ArrayList<>();

                for (int i = 0; i < sig_list.size(); i++) {
                    int[] tmp_source_sig = sig_list.get(i).clone();
                    p = tmp_source_sig.length;
                    for(int j=0;j<sig_list.size();j++)
                        if(i<j) {
                            Signal s = new Signal();
                            int[] tmp = sig_list.get(j).clone();
                            s.setSignal(tmp);
                            System.out.println("\nAFVK");

                            System.out.printf("КС №%d с КС №%d\n",i,j);
                           // System.out.println(Arrays.toString(tmp_source_sig));
                           // System.out.println(Arrays.toString(tmp));
                            pereodic_cross_correl_list = s.getApereodicCorrelList(tmp_source_sig, printflag);

                            int r_max = StatClass.getRmaxWO(pereodic_cross_correl_list, tmp_source_sig.length);
                            System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, tmp_source_sig.length) + "\u221A" + tmp_source_sig.length);
                            System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list, r_max));


                            rmax_list.add(r_max);
                            Plot chart = new Plot("АФВК",
                                    "АФВК"+", КС №"+i+", КС №"+j+", L = "+Integer.toString(p),pereodic_cross_correl_list);
                            chart.pack( );
                            RefineryUtilities.centerFrameOnScreen( chart );
                            chart.setVisible( true );
/*
                            Plot chart = new Plot("AФВК",
                                    "AФВК"+", КС №"+i+", КС №"+j+", L = "+Integer.toString(256)+", Rmax = "+r_max,pereodic_cross_correl_list);
                            chart.pack( );
                            RefineryUtilities.centerFrameOnScreen( chart );
                            chart.setVisible( true );
*/
                            StatClass.CalculateAndSet(pereodic_cross_correl_list,tmp_source_sig,avg_module_list,dispersion_module_list,deviation_module_list,true);



                            StatClass.CalculateAndSet(pereodic_cross_correl_list,tmp_source_sig,avg_list,dispersion_list,deviation_list,false);


                        }





                }
            }break;

        }

        StatClass.printStat(rmax_list,avg_module_list,dispersion_module_list,deviation_module_list,avg_list,dispersion_list,deviation_list,p);


    }


    public static void main(String[]args){

        CryptoSignal cs = new CryptoSignal();


        List<int[]> ansamble = new ArrayList<>();

        /*p = 16 if_rmax = 2
        * p = 32 if_rmax = 4
        * p = 64 if_rmax = 6
        * p = 128 if_rmax = 12
        * p = 256 if_rmax = 22
        * p = 512 if_rmax = 39
        * p = 1024 if_rmax = 75
        * p = 2048 if_rmax = 114*/
        cs.generateCryptoSignals(ansamble,32,16,4);

        StatClass.printListWithArr(ansamble);



            CryptoSignal.CryptoSignalStatistics(FVK.PFAK,ansamble,true);
            CryptoSignal.CryptoSignalStatistics(FVK.AFAK,ansamble,true);
            CryptoSignal.CryptoSignalStatistics(FVK.PFVK,ansamble,true);
            CryptoSignal.CryptoSignalStatistics(FVK.AFVK,ansamble,true);




    }
}
