import org.jfree.ui.RefineryUtilities;

import java.util.Arrays;
import java.util.List;

public class hadamar_test {


    public static void main(String[]args){
        int [][] h ;

        //ХДС для числа 17 с наменьшими АФАК Rmax = 2;
        int [][]arr = {{-1, 1, -1, -1, 1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1},
                {-1, -1, -1, -1, -1, 1, 1, 1, -1, 1, 1, -1, 1, -1, 1, 1}
                    };




        /*for(int i =0;i<arr.length;i++){
            if(arr[i]==-1){
                arr[i]=0;
            }
        }*/
        /*
        for(int i = 0;i<arr.length;i++){
            for (int j = 0; j<h.length; j++){
                tmp[i] = (arr[i]*h[i%h.length][j]);
            }
        }*/
/*

        int p = 16;
        Hadamar h1 = new Hadamar(p);
        int [][] tmp  = new int[arr.length][p];

        h = h1.getHadamarMatrix();
        System.out.println();


        //Модуляция сигналов
        for(int i = 0;i<arr.length;i++){
            int random_pos = 1 + (int) (Math.random() * h.length-1);// Случайный индекс из адамара

            System.out.printf("Сигнал №%d из матрицы Уолша-Адамара с сигналом ХДС №%d\n",random_pos,i);
            for (int j = 0; j<h.length; j++){
                tmp[i][j] = (arr[i][j]*h[random_pos][j]);
            }
        }

       StatClass.print2DArr(tmp);

        //Расчитаем ФАК
        for(int i =0;i<tmp.length;i++) {
            System.out.println("\nPFAK");

            int[] modulated_sig = tmp[i];//
            int[] shifted_sig = modulated_sig.clone();//for pereodic

            Signal s1 = new Signal(p);
            s1.setSignal(shifted_sig);

            System.out.println(Arrays.toString(modulated_sig));
            List<Integer> pereodic_auto_correl_list2 = s1.getAutoCorrelList(modulated_sig);


            int rmax =StatClass.getRmaxWO(pereodic_auto_correl_list2, p);
            System.out.println("\nRmax="+rmax);
            System.out.println("Count: "+StatClass.getCntAndPos(pereodic_auto_correl_list2,rmax));

            System.out.println("\nAFAK");
            int[] shifted_sig1 = modulated_sig.clone();//for apereodic
            Signal s2 = new Signal(p);
            s2.setSignal(shifted_sig1);

            System.out.println(Arrays.toString(modulated_sig));
            List<Integer> apereodic_auto_correl_list2 = s2.getApereodicCorrelList(modulated_sig);


            int rmax1 =StatClass.getRmaxWO(apereodic_auto_correl_list2, p);
            System.out.println("\nRmax="+rmax1);
            System.out.println("Count: "+StatClass.getCntAndPos(apereodic_auto_correl_list2,rmax1));

        }

        //расчитываем FVK



        int [] source_sig = tmp[0].clone();
        int[] shifted_sig = tmp[1].clone();
        int[] shifted_sig2 = tmp[1].clone();

        Signal s = new Signal(p);
        s.setSignal(shifted_sig);
        System.out.println("\nFVK\nSource:"+Arrays.toString(source_sig));
        System.out.println("Shifted:"+Arrays.toString(shifted_sig));


        System.out.println("PFVK");


        List<Integer> cross_pereodic = s.getAutoCorrelList(source_sig);

        int rmax =StatClass.getRmaxWO(cross_pereodic, p);
        System.out.println("\nRmax="+rmax);
        System.out.println("Count: "+StatClass.getCntAndPos(cross_pereodic,rmax));

        Signal s1 = new Signal(p);
        s1.setSignal(shifted_sig2);


        System.out.println("AFVK");
        List<Integer> cross_apereodic = s1.getApereodicCorrelList(source_sig);

        int rmax1 =StatClass.getRmaxWO(cross_apereodic, p);
        System.out.println("\nRmax="+rmax1);
        System.out.println("Count: "+StatClass.getCntAndPos(cross_apereodic,rmax1));

        */

    }
}
