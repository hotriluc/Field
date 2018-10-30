public class Signal {

    private int sig_arr[];
    private int R;

    public Signal(int p){
        sig_arr = new int[p-1];
    }

    public void setSignal(int[] arr){

        this.sig_arr = arr;
    }

    public int[] getSignal(){
        return this.sig_arr;
    }

    public void printSignal(){
        for(int i=0;i<sig_arr.length;i++){
            System.out.printf("%4d",sig_arr[i]);
        }
        //System.out.println();
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

    public int CalculatePAKF(int []arr,int []arr2){
        R=0;
        for(int i=0;i<arr.length;i++){
            int tmp=arr[i]*arr2[i];
            R+=tmp;
        }
        System.out.printf(" R = %d\n",R);


        return this.R;

    }

    public void decimation(int []arr, int []arr2, int d){
        for (int i=0;i<arr.length;i++){
            int pos = ((d+d*i))% arr.length;
            arr2[i]=arr[pos];
        }
    }



}
