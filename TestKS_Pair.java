import org.jfree.ui.RefineryUtilities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestKS_Pair {

    static long binomial(long n, long m) {
        m = Math.min(m, n - m);
        long res = 1;
        for (long i = 0; i < m; i++) {
            res = res * (n - i) / (i + 1);
        }
        return res;
    }

    public static void main(String[]args){
        int p = 127;
        AESSeq seq1 = new AESSeq();
        AESSeq seq2 = new AESSeq();
        List<Integer> pereodic_cross_correl_list;
        while (true) {
            int[] source_sig = seq1.genereteSeq(p);

            int[] arr2 = seq2.genereteSeq(p);

            System.out.println(Arrays.toString(source_sig));
            System.out.println(Arrays.toString(arr2));

            Signal s1 = new Signal(p);

            s1.setSignal(arr2);
            pereodic_cross_correl_list = s1.getAutoCorrelList(source_sig);

            int rmax = StatClass.getRmaxWO(pereodic_cross_correl_list, p);
            System.out.println("\nRmax:" + rmax);
            if(rmax<13) {
                break;
               /* Set s = new HashSet(pereodic_cross_correl_list);
                if(s.size()==3){
                break;}}*/

            }
        }

        /*Количество пар C из n по m*/
        long pair_cnt = binomial(p+2,2) ;
        System.out.println("Pair Count: "+pair_cnt);
        /*Каждые из пар образуют так же по 33 сигнала
        * изза того что повторяются 2 поделим на 2*/
        long total_capacity =  (p*pair_cnt)/2;
        System.out.println("Total Capacity: "+total_capacity);
        /*
        pereodic_cross_correl_list.addAll(pereodic_cross_correl_list);
        Plot pfvk_chart = new Plot("PFVK",
                "ПФВК"+", L = "+Integer.toString(p),pereodic_cross_correl_list);
        pfvk_chart.pack( );
        RefineryUtilities.centerFrameOnScreen( pfvk_chart );
        pfvk_chart.setVisible( true );*/
    }
}
