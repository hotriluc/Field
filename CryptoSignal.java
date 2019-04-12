import java.util.ArrayList;
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


            if(rmax<if_rmax){
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
                    List<Integer> pereodic_auto_correl_list = s.getApereodicCorrelList(tmp_source_sig, printflag);

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
                            System.out.println(i+", "+j);
                            pereodic_cross_correl_list = s.getPereodicCorrelList(tmp_source_sig, printflag);

                            int r_max = StatClass.getRmaxWO(pereodic_cross_correl_list, tmp_source_sig.length);
                            System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, tmp_source_sig.length) + "\u221A" + tmp_source_sig.length);
                            System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list, r_max));


                            rmax_list.add(r_max);

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
        cs.generateCryptoSignals(ansamble,256,20,30);

        StatClass.printListWithArr(ansamble);


       /* for (int i = 0; i < ansamble.size(); i++) {
            Signal s2 = new Signal();
            Signal s3 = new Signal();
            int[] tmp = ansamble.get(i).clone();
            int[] source_sig = ansamble.get(i).clone();
            s2.setSignal(tmp);
            s3.setSignal(tmp);
            System.out.println("\nPFAK");
            List<Integer> pereodic_cross_correl_list = new ArrayList<>();
            pereodic_cross_correl_list = s2.getPereodicCorrelList(source_sig, true);

            int r_max = StatClass.getRmaxWO(pereodic_cross_correl_list, source_sig.length);
            System.out.println("\nRmax=" + r_max + " = " + "x\u221AL = " + StatClass.getX(r_max, source_sig.length) + "\u221A" + source_sig.length);
            System.out.println("Count: " + StatClass.getCntAndPos(pereodic_cross_correl_list, r_max));

            System.out.println("\nAFAK");
            List<Integer> apereodic_cross_correl_list = new ArrayList<>();
            apereodic_cross_correl_list = s3.getApereodicCorrelList(source_sig, true);

            int r_max1 = StatClass.getRmaxWO(apereodic_cross_correl_list, source_sig.length);
            System.out.println("\nRmax=" + r_max1 + " = " + "x\u221AL = " + StatClass.getX(r_max1, source_sig.length) + "\u221A" + source_sig.length);
            System.out.println("Count: " + StatClass.getCntAndPos(apereodic_cross_correl_list, r_max1));


        }*/

            CryptoSignal.CryptoSignalStatistics(FVK.PFAK,ansamble,true);
            CryptoSignal.CryptoSignalStatistics(FVK.AFAK,ansamble,true);
            CryptoSignal.CryptoSignalStatistics(FVK.PFVK,ansamble,false);
        }
}
