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
        e.getCoprime();
        e.printCoprime();


        f1.fill_1strow();
        f1.fill_2ndrow();
        f1.fill_3rdrow();
        f1.fill_4throw();
        f1.fill_5throw();
        f1.fill_6throw();
        f1.printArray();
    }
}
