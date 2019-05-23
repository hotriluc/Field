public class Euclide {
    private int gcd;


    Euclide(){}

    public int getGCD(int a, int b){
        int c=0;
        do{
            c = a%b;
            a = b;
            b = c;
        }while (b!=0);
        this.gcd=Math.abs(a);

        return this.gcd;
    }

    public static void main(String[]args){

        System.out.println(new Euclide().getGCD(127,14));
    }
}
