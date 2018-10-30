import java.util.ArrayList;

public class Test {

    static void printArr(int arr[]){
        for(int i:arr){
            System.out.printf("%4d",i);
        }
    }

    public static void main (String args[]){
        int p = 11;
        int teta_min = 2;

        Field f1 = new Field(p);
        f1.printArray();

        System.out.println("P = "+p+"\nL = "+(p-1));



        //euler
        Euler e = new Euler(p-1);
        System.out.println("\u03D5("+(p-1)+") = "+e.getPhi());

        //with euclide algorithm searching for all coprime of n
         ArrayList<Integer> cl = e.getCoprime();
        e.printCoprime();

        /*Getting All Tetas*/
        System.out.println("Let \u03F4min = "+teta_min+" then");
        f1.getTetaList(teta_min,cl);
        f1.printTetaList();

        /*Setting Teta*/
        f1.setTeta(2);
        System.out.printf("\n\u03F4 = %d\n",f1.getTeta());

        /*FillingTable*/
        f1.fill_row_Ui();
        f1.fill_row_ai();
        f1.fill_row_Ai();
        f1.fill_row_bi();
        f1.fill_row_MH();
        f1.fill_row_Psi();
        f1.printArray();


//=======================PEREODICAUTOCORRELATIONFUNCTION==================================
        int[]b = f1.getPsi();//current signal
        int[]b1 = new int[p-1];//creating same signal which will be shifted
        for(int i =0;i<b.length;i++){
            b1[i]=b[i];
        }

        Signal s1 = new Signal(p);
        s1.setSignal(b1);
        s1.printSignal();
        s1.CalculatePAKF(b,s1.getSignal());

      for (int i=0;i<s1.getSignal().length;i++) {
           s1.CyclicShiftRight(1);
           s1.printSignal();
            s1.CalculatePAKF(b,s1.getSignal());
       }

//=========================================================





        int tmp_arr [] = new int[p-1];
        for(int i =0;i<b.length;i++){
            tmp_arr[i]=b[i];
        }
        System.out.println();
        System.out.println("Source Signal");
        printArr(tmp_arr);

        int tmp_arr1 [] = new int[p-1];
        int d =0;

        for(int i=0;i< cl.size();i++) {
            System.out.printf("\nDecimation Coef = %d\n",cl.get(i));
            s1.decimation(tmp_arr, tmp_arr1, cl.get(i));


            printArr(tmp_arr1);
        }

    }
}
