import org.jfree.ui.RefineryUtilities;

import java.util.Arrays;
import java.util.List;

public class hadamar_test {


    public static void main(String[]args){
        int [][] h = {{1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1},
                {1, 1, -1, -1, 1, 1, -1, -1, 1, 1, -1, -1, 1, 1, -1, -1}};

        int []arr = {1, 1, 1, -1, -1, -1, 1, 1, -1, -1, 1, -1, -1, 1, -1, 1};
        int []tmp  = new int[16];

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
       /* System.out.println();
        for(int i = 0;i<arr.length;i++){
            for (int j = 0; j<h.length; j++){
                tmp[i] = (arr[i]*h[0][j]);
            }
        }
        System.out.println(Arrays.toString(tmp));


        Signal s = new Signal(13);
        int[]source_sig = tmp.clone();
        int []shifted_sig= tmp.clone();
        s.setSignal(shifted_sig);

        List<Integer> pereodic_auto_corel_list = s.getAutoCorrelList(source_sig);
        */



    }
}
