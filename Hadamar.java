import java.util.Arrays;

public class Hadamar {

    private int[][] H;
    private int N;

    public Hadamar(int N){
        this.N = N;
        this.H = new int[N][N];
        H[0][0] = 1;
    }



    public int[][] getHadamarMatrix(){
        for(int n = 1;n < this.N; n+=n){
            for (int i = 0 ; i< n; i++)
                for (int j = 0; j< n; j++){
                    H[i+n][j] = H[i][j];
                    H[i][j+n] = H[i][j];
                    H[i+n][j+n] = -H[i][j];
                }
        }
        return this.H;
    }




    public static void main(String[]args) {
      /*  int N = 4;
        boolean[][] H = new boolean[N][N];
        H[0][0] = true;
        for(int n = 1; n < N; n += n)
        {
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                {
                    H[i+n][j] = H[i][j];
                    H[i][j+n] = H[i][j];
                    H[i+n][j+n] = !H[i][j];
                }
        }
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(H[i][j]) System.out.print("1 ");
                else        System.out.print("-1 ");
            }
            System.out.println();
        }*/

        Hadamar h1 = new Hadamar(16);

        int [][]arr1 = h1.getHadamarMatrix();

        for(int i = 0; i<arr1.length;i++){

                System.out.println(Arrays.toString(arr1[i]));


        }

    }
}
