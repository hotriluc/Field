import org.jfree.ui.RefineryUtilities;

import java.util.List;

public class sumtest {
public static void main(String[]args){
 int arr[] ={-1, -1, 1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, -1, 1, -1, -1, 1, -1, -1, -1, -1, -1, 1, 1, -1, -1, 1, -1, 1 };
 int arr2[] ={1, 1, -1, 1, 1, 1, -1, 1, -1, -1, 1, -1, 1, 1, 1, 1, -1, -1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 1, 1};
 int p = 31;
    Signal s1 = new Signal(p);

    s1.setSignal(arr2);

    List<Integer> pereodic_cross_correl_list = s1.getAutoCorrelList(arr);
    System.out.println("=================");
    int rmax = StatClass.getRmaxWO(pereodic_cross_correl_list, 31);
    System.out.println("\nRmax:" + rmax);


    List<int[]> list = s1.getListWithArrPereodic();
   System.out.println("\nList size:" + list.size());
   int cnt=0;

   for(int i = 0;i<list.size();i++){
      Signal s2 = new Signal(p);
      int []source_sig = list.get(i);
      for(int j = 0;j<list.size();j++){
         if(i!=j){
         int []shift_sig = list.get(j);
            s2.setSignal(shift_sig);
            List<Integer> pereodic_cross_correl_list2 = s2.getAutoCorrelList(source_sig);
            System.out.println();

            int rmax2 = StatClass.getRmaxWO(pereodic_cross_correl_list2, 31);
            if(rmax2<=rmax){
               cnt++;
               System.out.printf("Pair: %d , %d\n",i,j);
            }
         }else{continue;}

      }

   }
   System.out.println("Count: "+cnt);





}
}
