import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class hadamar_test_256  {

    public static void  CombinationsFVK(List<int[]> arr_list,int p,boolean printflag){
        for(int i =0;i<arr_list.size();i++){
            int [] source_sig = arr_list.get(i).clone();
            for(int j = 0;j<arr_list.size();j++) {
                if ((i <j) ) {
                    int[] shifted_sig = arr_list.get(j).clone();
                    int[] shifted_sig2 =arr_list.get(j).clone();

                    Signal s = new Signal(p);
                    s.setSignal(shifted_sig);
                    System.out.printf("\nFVK(%d,%d)\n", i, j);
                    System.out.println("Signal №"+i+": "+ Arrays.toString(source_sig));
                    System.out.println("Signal №"+j+": "+ Arrays.toString(shifted_sig));


                    System.out.println("PFVK");


                    List<Integer> cross_pereodic = s.getPereodicCorrelList(source_sig,printflag);

                    int r_max = StatClass.getRmaxWO(cross_pereodic, p);
                    System.out.println("\nRmax=" + r_max +" = "+"x\u221AL = "+StatClass.getX(r_max,p)+"\u221A"+p);
                    System.out.println("Count: " + StatClass.getCntAndPos(cross_pereodic, r_max));

                    int r_min = StatClass.getRmin(cross_pereodic);
                    System.out.printf("Rmin: %d \n",r_min);
                    System.out.println("Count: " + StatClass.getCntAndPos(cross_pereodic,r_min));

                    int r_mod = 0;
                    if (r_max>=(Math.abs(r_min))){
                        r_mod = r_max;
                    }else {r_mod = Math.abs(r_min);}



                    Signal s1 = new Signal(p);
                    s1.setSignal(shifted_sig2);

                    System.out.println("AFVK");
                    List<Integer> cross_apereodic = s1.getApereodicCorrelList(source_sig,printflag);

                    int rmax1 = StatClass.getRmaxWO(cross_apereodic, p);
                    System.out.println("\nRmax=" + rmax1+" = "+"x\u221AL = "+StatClass.getX(rmax1,p)+"\u221A"+p);
                    System.out.println("Count: " + StatClass.getCntAndPos(cross_apereodic, rmax1));
                }
            }
        }
    }

    public static List<Pair> CombinationsFVK(List<int[]> arr_list,int p,boolean printflag,int N){
        List<Pair> pairt_list = new ArrayList<>();
        for(int i =0;i<arr_list.size();i++){
            int [] source_sig = arr_list.get(i).clone();
            for(int j = 0;j<arr_list.size();j++) {
                if ((i <j) ) {
                    int[] shifted_sig = arr_list.get(j).clone();
                    int[] shifted_sig2 =arr_list.get(j).clone();

                    Signal s = new Signal(p);
                    s.setSignal(shifted_sig);

                    System.out.printf("\nFVK(%d,%d)\n", i, j);
                    System.out.println("Signal №"+i+": "+ Arrays.toString(source_sig));
                    System.out.println("Signal №"+j+": "+ Arrays.toString(shifted_sig));


                    System.out.println("PFVK");


                    List<Integer> cross_pereodic = s.getPereodicCorrelList(source_sig,printflag);

                    int r_max = StatClass.getRmaxWO(cross_pereodic, p);
                    System.out.println("\nRmax=" + r_max +" = "+"x\u221AL = "+StatClass.getX(r_max,p)+"\u221A"+p);
                    System.out.println("Count: " + StatClass.getCntAndPos(cross_pereodic, r_max));

                    int r_min = StatClass.getRmin(cross_pereodic);
                    System.out.printf("Rmin: %d \n",r_min);
                    System.out.println("Count: " + StatClass.getCntAndPos(cross_pereodic,r_min));

                    int r_mod = 0;
                    if (r_max>=(Math.abs(r_min))){
                        r_mod = r_max;
                    }else {r_mod = Math.abs(r_min);}

                    if(r_mod<=N){
                        pairt_list.add(new Pair(i,j));
                    }


                    Signal s1 = new Signal(p);
                    s1.setSignal(shifted_sig2);

                    System.out.println("AFVK");
                    List<Integer> cross_apereodic = s1.getApereodicCorrelList(source_sig,printflag);

                    int rmax1 = StatClass.getRmaxWO(cross_apereodic, p);
                    System.out.println("\nRmax=" + rmax1+" = "+"x\u221AL = "+StatClass.getX(rmax1,p)+"\u221A"+p);
                    System.out.println("Count: " + StatClass.getCntAndPos(cross_apereodic, rmax1));
                }
            }
        }
        return pairt_list;
    }




    public static void main(String[]args){
        int [][] h256;

        int arr[][] = {
                {-1, 1, 1, -1, 1, -1, -1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1, 1, -1, -1, -1, 1, -1, -1, 1, -1, 1, 1, 1, -1, 1, -1, 1, -1, -1, 1, 1, 1, -1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, -1, 1, -1, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 1, 1, -1, -1, -1, -1, -1, -1, 1, -1, 1, -1, 1, 1, -1, 1, -1, -1, 1, 1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, -1, 1, 1, -1, -1, 1, -1, -1, 1, 1, -1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, 1, -1, 1, -1, 1, 1, 1, 1, 1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, 1, -1, 1, -1, 1, 1, -1, -1, 1, 1, 1, -1, 1, -1, 1, -1, 1, 1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, -1, 1, 1, 1, 1, -1, 1, 1, 1, 1, -1, 1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, -1, 1, 1, -1, 1, -1, 1, 1, -1, -1, 1, -1, 1, 1, 1, 1, -1, -1, -1, -1, 1, 1, 1},
                {1, 1, -1, -1, -1, 1, 1, 1, -1, 1, -1, 1, -1, -1, 1, -1, -1, -1, 1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, 1, -1, -1, 1, 1, -1, 1, -1, -1, 1, -1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1, 1, -1, 1, -1, 1, -1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, -1, 1, -1, 1, 1, -1, -1, 1, -1, -1, -1, 1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, -1, -1, 1, 1, 1, -1, -1, 1, -1, -1, -1, -1, -1, 1, 1, 1, -1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, 1, 1, -1, -1, 1, -1, 1, -1, -1, 1, 1, -1, 1, -1, -1, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, 1, 1, -1, 1, 1, -1, 1, -1, -1, 1, -1, 1, 1, 1, -1, 1, 1, 1, -1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, -1, 1, -1, -1, 1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1},
                {1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, -1, 1, -1, 1, 1, -1, -1, 1, -1, -1, 1, 1, 1, 1, -1, 1, 1, -1, 1, -1, -1, 1, -1, 1, -1, -1, 1, 1, 1, -1, -1, -1, -1, 1, -1, 1, -1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -1, 1, 1, -1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, 1, 1, -1, 1, -1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1, -1, -1, 1, 1, -1, 1, -1, 1, -1, 1, 1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, -1, -1, -1, 1, 1, -1, 1, -1, -1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 1, -1, -1, -1, 1, -1, 1, -1, 1, 1, 1, -1, 1, 1, -1, 1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, -1, 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, 1, 1, -1, 1, 1, -1, -1, -1, -1, -1, 1, 1, 1, 1, -1, -1, -1, 1, -1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, 1, -1, -1, 1, -1, -1, 1, 1, -1, -1, 1},
                {1, 1, -1, -1, -1, 1, -1, 1, 1, -1, 1, -1, -1, 1, 1, -1, -1, 1, -1, 1, -1, 1, 1, 1, 1, 1, 1, -1, 1, 1, -1, -1, 1, -1, 1, 1, 1, -1, -1, 1, -1, -1, 1, 1, -1, 1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 1, 1, -1, 1, 1, -1, -1, 1, -1, 1, 1, -1, 1, 1, 1, -1, 1, 1, -1, 1, -1, 1, 1, -1, 1, -1, 1, -1, 1, -1, 1, 1, 1, -1, 1, -1, -1, -1, 1, 1, 1, 1, -1, 1, -1, -1, -1, -1, 1, 1, 1, -1, 1, -1, -1, 1, 1, 1, -1, -1, 1, -1, -1, -1, 1, 1, -1, -1, 1, -1, -1, -1, 1, 1, -1, 1, 1, -1, -1, -1, -1, 1, -1, -1, 1, -1, 1, 1, 1, 1, -1, 1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, -1, -1, -1, 1, -1, -1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, -1, 1, -1, -1, -1, 1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, 1, -1, 1, 1, 1, 1, 1, 1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1, 1, 1, -1, 1, 1, -1, 1},
                {-1, -1, 1, 1, -1, 1, -1, 1, 1, -1, -1, -1, -1, 1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, 1, 1, 1, -1, -1, -1, 1, 1, 1, -1, 1, -1, 1, 1, -1, -1, -1, 1, 1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1, 1, -1, 1, 1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, 1, 1, -1, -1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -1, 1, -1, 1, -1, 1, -1, 1, 1, 1, 1, 1, 1, -1, -1, 1, -1, 1, 1, -1, -1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, 1, -1, -1, 1, -1, -1, 1, 1, -1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 1, -1, -1, -1, -1, 1, -1, 1, 1, 1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, -1, 1, -1, 1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, -1, -1, 1, 1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, 1, 1, 1, -1, 1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1},
                {-1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1, -1, 1, -1, -1, 1, 1, 1, -1, -1, -1, -1, 1, 1, -1, 1, 1, 1, 1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, 1, 1, -1, 1, 1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1, -1, 1, -1, 1, 1, 1, -1, 1, -1, 1, -1, -1, 1, -1, -1, 1, 1, -1, 1, -1, 1, 1, 1, 1, 1, -1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, -1, 1, 1, -1, 1, -1, 1, -1, 1, -1, -1, 1, -1, 1, 1, -1, -1, 1, 1, -1, -1, 1, 1, -1, -1, 1, 1, -1, -1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, 1, -1, 1, -1, -1, -1, 1, -1, 1, -1, -1, 1, -1, 1, -1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, 1, -1, -1, -1, 1, -1, -1, -1, 1, 1, 1, 1, -1, 1, -1, -1, -1, -1, -1, 1, -1, -1, 1, -1, 1, 1, 1, -1, -1, 1, -1, 1, 1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, 1, -1, 1, -1, -1, 1, 1},
                {-1, -1, -1, -1, -1, -1, 1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, -1, -1, -1, 1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, 1, -1, 1, -1, 1, 1, 1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, 1, 1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, -1, 1, -1, 1, 1, -1, -1, 1, 1, 1, -1, 1, -1, -1, 1, -1, 1, 1, 1, -1, -1, -1, 1, 1, -1, -1, -1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, -1, 1, -1, -1, 1, -1, -1, 1, 1, 1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -1, 1, 1, 1, -1, 1, 1, -1, -1, 1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, -1, 1, 1, -1, -1, 1, 1, 1, 1, -1, 1, -1, 1, -1, -1, 1, 1, -1, 1, 1, 1, 1, 1, -1, 1, -1, -1, 1, 1, 1, 1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, 1, -1, -1, -1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, -1, 1, -1, -1, 1, -1, 1, 1, 1, -1, 1, -1, -1, 1, -1, 1, -1, 1, 1},


        };

        List<int[]> arr_list = new ArrayList<>();
        for(int i=  0;i<arr.length;i++){
        arr_list.add(arr[i]);
        }


        int p = 256;
        Hadamar h = new Hadamar(p);


        int sig_count = 20;
        int [][] tmp  = new int[sig_count][p];


        //int [][] tmp  = new int[sig_count][p];
        List<int[]> tmplis = new ArrayList<>();

        h256 = h.getHadamarMatrix();

        System.out.println("========================");
        System.out.println("Отобранные сигналы ХДС:");
        StatClass.printListWithArr(arr_list);
        System.out.println("========================");

      //  CombinationsFVK(arr_list,p,false);


        System.out.println("========================");
        System.out.println("Модуляция:");
        //Модуляция сигналов
      /*  for(int i = 0;i<arr_list.size();i++){
            int random_pos = 1 + (int) (Math.random() * h256.length-1);// Случайный индекс из адамара

            System.out.printf((i)+") Строка №%d из матрицы Уолша-Адамара с сигналом ХДС №%d:\n",random_pos,i%arr.length);
            System.out.println("Адамар:"+Arrays.toString(h256[random_pos]));
            System.out.println("ХДС:"+Arrays.toString(arr[i]));

            for (int j = 0; j<h256.length; j++){
                tmp[i][j] = (arr_list.get(i)[j]*h256[random_pos][j]);
            }

            tmplis.add(tmp[i]);
        }*/

        for(int i = 0;i<sig_count;i++){
            int random_pos = 1 + (int) (Math.random() * h256.length-1);// Случайный индекс из адамара

            System.out.printf((i)+") Строка №%d из матрицы Уолша-Адамара с сигналом ХДС №%d:\n",random_pos,i%arr_list.size());
            System.out.println("Адамар:"+Arrays.toString(h256[random_pos]));
            System.out.println("ХДС:"+Arrays.toString(arr[i%arr_list.size()]));

            for (int j = 0; j<h256.length; j++){
                tmp[i][j] = (arr_list.get(i%arr_list.size())[j]*h256[random_pos][j]);
            }

            tmplis.add(tmp[i]);
        }

        System.out.println("========================");
        System.out.println("Производные:");
        StatClass.printListWithArr(tmplis);
        System.out.println("========================");

        //Расчитаем ФАК
        /*
        for(int i =0;i<tmp.length;i++) {
            System.out.println("\nPFAK для сигнала №"+i);

            int[] modulated_sig = tmp[i];//
            int[] shifted_sig = modulated_sig.clone();//for pereodic

            Signal s1 = new Signal(p);
            s1.setSignal(shifted_sig);

            //System.out.println(Arrays.toString(modulated_sig));
            List<Integer> pereodic_auto_correl_list2 = s1.getAutoCorrelList(modulated_sig);


            int rmax =StatClass.getRmaxWO(pereodic_auto_correl_list2, p);
            System.out.println("\nRmax="+rmax);
            System.out.println("Count: "+StatClass.getCntAndPos(pereodic_auto_correl_list2,rmax));

            System.out.println("\nAFAK для сигнала №"+i);
            int[] shifted_sig1 = modulated_sig.clone();//for apereodic
            Signal s2 = new Signal(p);
            s2.setSignal(shifted_sig1);

            //System.out.println(Arrays.toString(modulated_sig));
            List<Integer> apereodic_auto_correl_list2 = s2.getApereodicCorrelList(modulated_sig);


            int rmax1 =StatClass.getRmaxWO(apereodic_auto_correl_list2, p);
            System.out.println("\nRmax="+rmax1);
            System.out.println("Count: "+StatClass.getCntAndPos(apereodic_auto_correl_list2,rmax1));

        }
        */
        /*
        for(int i =0;i<tmp.length;i++){
            int [] source_sig = tmp[i].clone();
            for(int j = 0;j<tmp.length;j++) {
                if ((i <j) ) {
                    int[] shifted_sig = tmp[j].clone();
                    int[] shifted_sig2 = tmp[j].clone();

                    Signal s = new Signal(p);
                    s.setSignal(shifted_sig);
                    System.out.printf("\nFVK(%d,%d)\n", i, j);
                    System.out.println("Source:" + Arrays.toString(source_sig));
                    System.out.println("Shifted:" + Arrays.toString(shifted_sig));


                    System.out.println("PFVK");


                    List<Integer> cross_pereodic = s.getAutoCorrelList(source_sig);

                    int rmax = StatClass.getRmaxWO(cross_pereodic, p);
                    System.out.println("\nRmax=" + rmax);
                    System.out.println("Count: " + StatClass.getCntAndPos(cross_pereodic, rmax));

                    Signal s1 = new Signal(p);
                    s1.setSignal(shifted_sig2);


                    System.out.println("AFVK");
                    List<Integer> cross_apereodic = s1.getApereodicCorrelList(source_sig);

                    int rmax1 = StatClass.getRmaxWO(cross_apereodic, p);
                    System.out.println("\nRmax=" + rmax1);
                    System.out.println("Count: " + StatClass.getCntAndPos(cross_apereodic, rmax1));
                }
            }
        }
        */
        List<Pair> lp = new ArrayList<>();
        lp = CombinationsFVK(tmplis,p,false,40);
        for(Pair pair:lp){
            System.out.println(pair.getX()+" "+ pair.getY());
        }
    }
}
