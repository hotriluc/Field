import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Signal {

    private int sig_arr[];//сигнал
    private int R;//значение коеф кореляции
    private List<Integer> correl_List;//Лист хранящий значения коеф кореляции на каждой сдвижке сигнала
    private List<int[]> list_w_arr;//Лист хранящий сдвижки сигнала(кодовые последовательности)
    private List<int[]> decimation_list;//ХДС ДЛЯ РАЗНЫХ КОЕФ ДЕЦИМАЦИИ

    public Signal(){

            //sig_arr = new int[p-1];
            correl_List = new ArrayList<>();
            list_w_arr = new ArrayList<>();
            decimation_list = new ArrayList<>();

    }

    public Signal(int p){
        sig_arr = new int[p-1];
        correl_List = new ArrayList<>();
        list_w_arr = new ArrayList<>();
        decimation_list = new ArrayList<>();
    }

    public void setSignal(int[] arr){

        this.sig_arr = arr;
    }

    public int[] getSignal(){
        return this.sig_arr;
    }

    public void printSignal(){
        /*for(int i=0;i<sig_arr.length;i++){
            System.out.printf("%4d",sig_arr[i]);
        }*/
        System.out.print(Arrays.toString(sig_arr));
        System.out.println();
    }

    public void CyclicShiftRight(int pos){
        int size = sig_arr.length;
        for (int i = 0; i < pos; i++) {
            int temp = sig_arr[size-1];
            for (int j = size-1; j > 0; j--) {
                sig_arr[j] = sig_arr[j-1];
                //a[j-1] = 0;
            }
            sig_arr[0] = temp;
        }

    }

    public void ShiftRight(int pos){
        int size = sig_arr.length;
        for (int i = 0; i < pos; i++) {
            int temp = sig_arr[size-1];
            for (int j = size-1; j > 0; j--) {
                sig_arr[j] = sig_arr[j-1];

            }
            sig_arr[0] = 0;
        }

    }

    public int CalculateCorrelationCoef(int []arr,int []arr2){
        R=0;
        for(int i=0;i<arr.length;i++){
            int tmp=arr[i]*arr2[i];
            R+=tmp;
        }
       // System.out.printf(" R = %d ",R);//ЗАКОМЕНТИРОВАТЬ ЧТОБЫ ВМЕЩАЛОСЬ В КОНСОЛЬ ЕСЛИ БОЛЬШОЙ ПЕРИОД


        return this.R;

    }

    public void decimation(int []arr, int []arr2, int d){
        for (int i=0;i<arr.length;i++){
            int pos = ((d+d*i))% arr.length;
            arr2[i]=arr[pos];
        }
    }

    public List<int[]> getDecimation_list(int[]source_signal,List<Integer> coprime_list) throws IOException{
        int p = source_signal.length;
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("Decimation.txt"))) {

            for (int i = 0; i < coprime_list.size(); i++) {
                int tmp_arr1 [] = new int[p];
                //System.out.printf("\nDecimation Coef = %d\n", cl.get(i));
                decimation(source_signal, tmp_arr1, coprime_list.get(i));
                //printArr(tmp_arr1);
                decimation_list.add(tmp_arr1);
                bf.write(Arrays.toString(tmp_arr1)+"\n");
                bf.flush();
            }

        }
        return this.decimation_list;
    }

    /*Лист хранящий каждый сдвиг Переодической функции корелляции
    * */
    public List<int[]> getListWithArrPereodic(){

        return this.list_w_arr;
    }


    /*List with  Correlation Coef Pereodic
     * */
    public List<Integer> getPereodicCorrelList(int []arr, boolean print_flag){
        list_w_arr.clear();
        int cnt=0;
        int r = CalculateCorrelationCoef(arr, getSignal());
        correl_List.add(r);
        if(print_flag==true){
            System.out.printf(" R = %d ",r);
        }

        for (int i = 0; i < getSignal().length; i++) {
            CyclicShiftRight(1);
            int tmp_arr[] = sig_arr.clone();
            list_w_arr.add(tmp_arr);

            // printSignal();
            r    = CalculateCorrelationCoef(arr, getSignal());
            correl_List.add(r);

            if(print_flag==true){
                System.out.printf(" R = %d ",r);
            }
            cnt++;

        }
        return this.correl_List;
    }

    /*List with  Correlation Coef Apereodic
    * */
    public  List<Integer>  getApereodicCorrelList(int []arr,boolean print_flag){
        int cnt = 0;
        // printSignal();
        int r = CalculateCorrelationCoef(arr,getSignal());
        if(print_flag==true){
            System.out.printf(" R = %d ",r);
        }
        correl_List.add(r);

        for (int i=0;i<getSignal().length;i++) {
            ShiftRight(1);
            //printSignal();
            r = CalculateCorrelationCoef(arr,getSignal());
            if(print_flag==true){
                System.out.printf(" R = %d ",r);
            }
            correl_List.add(r);
            cnt++;
        }

        return this.correl_List;
    }
    /*
    * String desc - desciption or header of text file
    * String path - path to file
    *
    * */

    public void printCorrelList(){
            for(int i:correl_List){
                System.out.printf("R = %d ",i);
            }
    }



    public static void CaclculatePFVK_between_Pair(int[]sig_1,int[]sig_2,boolean printflag){
        Signal s = new Signal();
        s.setSignal(sig_2);
        List<Integer> cross_pereodic = s.getPereodicCorrelList(sig_1,printflag);
        System.out.println("\nPFVK");
        int r_max = StatClass.getRmaxWO(cross_pereodic, sig_1.length);
        System.out.println("Rmax=" + r_max +" = "+"x\u221AL = "+StatClass.getX(r_max,sig_1.length)+"\u221A"+sig_1.length);
        System.out.println("Count: " + StatClass.getCntAndPos(cross_pereodic, r_max));

        int r_min = StatClass.getRmin(cross_pereodic);
        System.out.printf("Rmin: %d \n",r_min);
        System.out.println("Count: " + StatClass.getCntAndPos(cross_pereodic,r_min)+"\n");


    }



}
