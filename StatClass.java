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

    /*Getting max in list Для ФВК */
    static int getRmax(List<Integer> list){

        return  Collections.max(list);
    }
    /*Getting max in list ФВК*/
    static int getRmin(List<Integer> list){
        return  Collections.min(list);
    }

    /*Getting max in list w/o specific value Для ФАК*/
    static int getRmaxWO(List<Integer> list,int val){
        int r_max = 0;
       for(int i =0;i<list.size();i++){
           if(list.get(i)==val){continue;}
           else if(list.get(i)>r_max){
               r_max = list.get(i);

           }
       }
       return r_max;
    }



    /*Подсчет конкретного количества значеиня в листе*/
    static int getCntAndPos(List<Integer> list,int searchValue){
        int sum =0;
        System.out.print("Position: ");
        for (int i = 0;i<list.size();i++){
            if(list.get(i).equals(searchValue)) {

                System.out.print(i+" ");
                sum++;
            }
        }

        return sum;
    }
}
