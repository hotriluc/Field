public class test2 {

    public static  void  main(String args[]){
        /*Расчеты для M-послед*/

        double x = 6;
        int L = 2047;
        int m = 11;
        double Rb_max = x*Math.sqrt(L);

        System.out.printf("x = Rmax/\u221AL = %d/\u221A%d = %f\n",Math.round(Rb_max),L,x);
        Euler e = new Euler(L);
        int eil_cnt = e.getPhi();
        System.out.println("\u03D5("+(L)+") = "+eil_cnt);
        System.out.println("Signal count: "+eil_cnt/m);

    }
}
