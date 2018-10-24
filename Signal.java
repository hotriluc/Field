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
            System.out.print(signal_arr[i]+" ");
        }
        System.out.println();
    }


}
