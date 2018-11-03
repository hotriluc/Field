import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    static void printArr(int arr[]){
        for(int i:arr){
            System.out.printf("%4d",i);
        }
    }

    public static void main (String args[]) throws IOException {
        int p =13;
        int teta_min = 2;

        Field f1 = new Field(p);


        System.out.println("P = "+p+"\nL = "+(p-1));


        //euler
        Euler eil = new Euler(p-1);
        System.out.println("\u03D5("+(p-1)+") = "+eil.getPhi());

        //with euclide algorithm searching for all coprime of n
         ArrayList<Integer> cl = eil.getCoprime();
        eil.printCoprime();
        Euler eil2 = new Euler(p);
        System.out.println("\u03D5("+(p)+") = "+eil2.getPhi());
        ArrayList<Integer> pl = eil2.getPrime();
        for(int i:pl){
            System.out.println(i);
        }

       // f1.getTetaMin(2,cl);
        /*Getting All Tetas*/
      /*  System.out.println("Let \u03F4min = "+teta_min+" then");
        f1.getTetaList(teta_min,cl);
        f1.printTetaList();*/

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
        int[]b1 ;//creating same signal which will be shifted
        int[]b2;
        b1 = b.clone();
        b2 = b.clone();
        Signal s1 = new Signal(p);
        s1.setSignal(b1);

        s1.printPereodicCorrel(b);

        System.out.println();
        Signal s2 = new Signal(p);
        s2.setSignal(b2);
      s2.printApereodicCorrel(b);

       /* s1.printSignal();
        s1.CalculatePAKF(b,s1.getSignal());

      for (int i=0;i<s1.getSignal().length;i++) {
           s1.CyclicShiftRight(1);
           s1.printSignal();
            s1.CalculatePAKF(b,s1.getSignal());
       }*/

//=========================================================






        System.out.println();
        System.out.println("Source Signal");
        printArr(b);

        int tmp_arr1 [] = new int[p-1];

        try (FileWriter f = new FileWriter("Decimation.txt")) {

            for (int i = 0; i < cl.size(); i++) {
              //  System.out.printf("\nDecimation Coef = %d\n", cl.get(i));
                s1.decimation(b, tmp_arr1, cl.get(i));
                //printArr(tmp_arr1);
                f.write(Arrays.toString(tmp_arr1));
                f.flush();
            }

        }

        System.out.println("\n");

    }
}
