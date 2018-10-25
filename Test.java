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


        int[]b = f1.getPsi();
        int[]b1 = new int[p-1];

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











    }
}
