import java.util.Collections;
import java.util.List;

public class StatClass {
    static void printArr(int arr[]){
        for(int i:arr){
            System.out.printf("%4d",i);
        }
    }

    public static boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    static int getRmax(List<Integer> list){
        return  Collections.max(list);
    }
    static int getRminCnt(List<Integer> list){

        int cnt_min = 0;
        int min = list.get(0);

        for(int i=0;i<list.size();i++){
            if(list.get(i)==min){
                cnt_min++;
            }else if(list.get(i)<min){
                min = list.get(i);
                cnt_min = 1;
            }
        }
        return cnt_min;
    }

    static int getRmin(List<Integer> list){
        return  Collections.min(list);
    }

    static int getRmaxCnt(List<Integer> list){

        int cnt_max = 0;
        int max = list.get(0);


        for(int i=0;i<list.size();i++){
            if(list.get(i)==max){
                cnt_max++;
            }else if(list.get(i)>max){
                max = list.get(i);
                cnt_max = 1;
            }
        }
        return cnt_max;
    }
}
