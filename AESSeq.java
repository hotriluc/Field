import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//AES PRN TEST

public class AESSeq {

    public static  void  main(String args[]){
        SecureRandom random = new SecureRandom();
        int p = new Scanner(System.in).nextInt();
        int []arr = new int[p];

        for(int i =0;i<arr.length;i++){

            int a = random.nextInt(2);
            if(a==0) {
                arr[i] = -1;
            }else
                arr[i]=a;
        }
        //Source Signal arr[]
        System.out.print(Arrays.toString(arr));
        Signal s1 = new Signal(p);
        int [] arr1 = arr.clone(); // will be shifted
        s1.setSignal(arr1);
        System.out.println("PFAK");

        List<Integer> pereodic_auto_correl_list=s1.getAutoCorrelList(arr);


    }
}
