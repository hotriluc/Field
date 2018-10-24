import java.util.ArrayList;

public class Test {

    public static void main (String args[]){
        int p = 13;
        Field f1 = new Field(p);
        f1.printArray();

        System.out.println("P = "+p+"\nL = "+(p-1));



        //euler
        Euler e = new Euler(p-1);
        System.out.println("\u03D5("+(p-1)+") = "+e.getPhi());

        //with euclide algorithm searching for all coprime of n
         ArrayList<Integer> cl = e.getCoprime();
        e.printCoprime();

        int teta_min = 2;
        System.out.println("Let \u03F4min = "+teta_min+" then");
        f1.getTetaList(teta_min,cl);
        f1.printTetaList();


        f1.fill_row_Ui();
        f1.fill_row_ai();
        f1.fill_row_Ai();
        f1.fill_row_bi();
        f1.fill_row_MH();
        f1.fill_row_Psi();
        f1.printArray();


        int [] a = {1,2,3,4};

        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }


        int size = a.length;
        for (int i = 0; i < 1; i++) {
            int temp = a[size-1];
            for (int j = size-1; j > 0; j--) {
                a[j] = a[j-1];
                //a[j-1] = 0;
            }
            a[0] = temp;
        }

        System.out.println();

        for (int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();

        int[]b = f1.getPsi();

       Signal s1 = new Signal(p);
       s1.setSignal(b);
       s1.printSignal();
       for (int i=0;i<s1.getSignal().length;i++) {
           s1.CyclicShiftRight(1);
           s1.printSignal();
       }
    }
}
