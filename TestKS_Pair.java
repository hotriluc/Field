import org.jfree.ui.RefineryUtilities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestKS_Pair {


    public static void main(String[]args){
        int p = 31;
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
            if(rmax<=1.5*Math.sqrt(p)) {
                break;
               /* Set s = new HashSet(pereodic_cross_correl_list);
                if(s.size()==3){
                break;}}*/

            }
        }

        pereodic_cross_correl_list.addAll(pereodic_cross_correl_list);
        Plot pfvk_chart = new Plot("PFVK",
                "ПФВК"+", L = "+Integer.toString(p),pereodic_cross_correl_list);
        pfvk_chart.pack( );
        RefineryUtilities.centerFrameOnScreen( pfvk_chart );
        pfvk_chart.setVisible( true );
    }
}
