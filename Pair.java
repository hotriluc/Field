public class Pair {
    private int x;
    private int y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void printPair(){
        System.out.printf("Pair(%d, %d",this.x,this.y);
    }


}
