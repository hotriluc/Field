import org.jfree.ui.RefineryUtilities;

import java.util.List;

public class sumtest {
public static void main(String[]args){
 int arr[] = {1, 1, -1, -1, 1, 1, -1, 1, 1, -1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, 1, -1, 1, -1, -1, 1, -1, 1};
    int arr2[] = {1, 1, 1, -1, -1, 1, 1, -1, 1, 1, -1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, 1, -1, 1, -1, -1, 1, -1};
    Signal s1 = new Signal(31);

    s1.setSignal(arr2);
    List<Integer> pereodic_cross_correl_list = s1.getAutoCorrelList(arr);
    System.out.println("=================");
    int rmax = StatClass.getRmaxWO(pereodic_cross_correl_list, 31);
    System.out.println("\nRmax:" + rmax);






}
}
