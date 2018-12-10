import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class test3 {

    public static  void  main(String args[]){
        SecureRandom random = new SecureRandom();
        int []arr = new int[32];

        for(int i =0;i<arr.length;i++){

            int a = random.nextInt(2);
            if(a==0) {
                arr[i] = -1;
            }else
                arr[i]=a;
        }
        System.out.print(Arrays.toString(arr));
        Signal s1 = new Signal(32);
        s1.setSignal(arr);
        int [] arr1 = arr.clone();
        System.out.println("PFAK");

        List<Integer> pereodic_auto_correl_list=s1.getAutoCorrelList(arr1);
    }
}
