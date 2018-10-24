public class Signal {

    private int signal_arr[];

    public Signal(int p){
        signal_arr = new int[p-1];
    }

    public void setSignal(int[] arr){
        this.signal_arr = arr;
    }

    public int[] getSignal(){
        return this.signal_arr;
    }

    public void printSignal(){
        for(int i=0;i<signal_arr.length;i++){
            System.out.printf("%4d",signal_arr[i]);
        }
        System.out.println();
    }

    public void CyclicShiftRight(int pos){
        int size = signal_arr.length;
        for (int i = 0; i < pos; i++) {
            int temp = signal_arr[size-1];
            for (int j = size-1; j > 0; j--) {
                signal_arr[j] = signal_arr[j-1];
                //a[j-1] = 0;
            }
            signal_arr[0] = temp;
        }

    }

}
