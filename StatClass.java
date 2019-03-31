import java.util.*;

public class StatClass {


    static boolean checkP(int p){

        if (StatClass.isPrime(p)) {
            System.out.println("prime");
            return true;
        } else {
            System.out.println("no prime");
            return false;
        }
    }
    static double getX(int rmax, int L){

        return rmax/Math.sqrt(L);
    }
    static void printArr(int arr[]){
        for(int i:arr){
            System.out.printf("%4d",i);
        }
    }

    static  void print2DArr(int arr[][]){
        for(int i = 0; i<arr.length;i++){

            System.out.println(Arrays.toString(arr[i]));


        }
    }

    static void printListWithArr(List<int[]> list){
        for(int i = 0; i<list.size();i++){

            System.out.println(i+") "+Arrays.toString(list.get(i)));


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

    public
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


    static  double CalculateAVG(List<? extends Number> x_list){
        double avg=0.0;

        for(Number n : x_list){
            avg += n.doubleValue();
        }

        return avg/x_list.size();
    }

    static  double CalculateDispersion(List<? extends Number> x_list, double avg_x){
        double dispersion=0.0;

        for(Number n : x_list){
            dispersion += Math.pow(n.doubleValue()-avg_x,2);
        }

        return dispersion/x_list.size();
    }

    static double CalculateDeviation(double dispersion){
        return Math.sqrt(dispersion);
    }
    static void ModuleSignal(List<Integer> list){
        for (int i =0;i<list.size();i++){
            int tmp = list.get(i);
            if(list.get(i)<0){
                list.set(i,-list.get(i));
            }
        }
    }




    /*if flag = true расчет для модулей выбросов
    * if flag = false расчет для всех боковых выбросов*/
    static void CalculateAndSet(List<Integer> correl_list,int[]source_sig,List<Double> avg_list,List<Double> dispersion_list,List<Double> deviation_list,boolean flag ){
        List<Integer> tmp_list = new ArrayList<>();
        tmp_list.addAll(correl_list);

        if (flag==true){
            StatClass.ModuleSignal(tmp_list);
        }

        double avg_x = StatClass.CalculateAVG(tmp_list);
        System.out.println("AVG_module:" + avg_x / Math.sqrt(source_sig.length));
        avg_list.add(StatClass.CalculateAVG(tmp_list));

        //дисперсия модулей
        double dispersion_x = StatClass.CalculateDispersion(tmp_list, avg_x);
        System.out.println("Dispersion_Module:" + dispersion_x / Math.sqrt(source_sig.length));
        dispersion_list.add(dispersion_x);

        //СКО модулей
        double deviation_x = Math.sqrt(dispersion_x);
        System.out.println("Deviation_Module:" + deviation_x / Math.sqrt(source_sig.length));
        deviation_list.add(deviation_x );
    }



}
