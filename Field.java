import java.util.ArrayList;
import java.util.function.IntToDoubleFunction;

public class Field {

    private int [][] array;
    private int P;
    private int teta;
    private int teta_arr_list;



    public Field (int P){
        this.array=new int[6][P];
        this.P=P;
        this.teta=2;
    }


    public void setArray(int[][] array,int P) {

        this.array = new int[6][P];
    }

    public  int[][] getArray(){

        return this.array;
    }

    public int getP(){

        return this.P;
    }
    public void setP(int P){

        this.P=P;
    }

    public ArrayList<Integer> getTetaList(int teta,ArrayList<Integer> coprime_arr_list){
        for(int i=0;i<coprime_arr_list.size();i++){
            int tmp = coprime_arr_list.get(i);
                tmp = teta;
        }
    }

    public  int getTeta(){

        return this.teta;
    }

    public void setTeta(int teta){
        this.teta=teta;
    }

    public void fill_1strow(){
        for (int i=0;i<getP()-1;i++){
            array[0][i]=i+1;
        }
    }

    public void fill_2ndrow(){

        for(int i=0;i<getP()-1;i++){
            array[1][i]= (int)Math.pow(teta,i) % P ;
        }
    }

    /*Заполнение Ai*/
    public void fill_3rdrow(){
        int tmp=0;
        int position=0;
        for(int i=0;i<getP()-1;i++){
            tmp =array[0][i];//Из первой строки Ui выибираем эл

            position=array[1][i];//Каждой Ui соответствует ai, запоминаем ai в качестве позиции для Ai
            array[2][position-1]=tmp% P ;//Заносим Ui в сохраненую позицию
        }
    }

    public void fill_4throw(){
        int tmp=0;
        for (int i=0;i<getP()-1;i++){
            tmp=array[1][i];
            array[3][i]=(tmp+1)% P ;
            if(array[3][i]==(0)){
                array[3][i]=1;
            }
        }
    }

    public void fill_5throw(){
        int pos1,pos2;
        int tmp=0;
        for(int i=0;i<getP()-1;i++){
            pos1=array[3][i];//значение bi cохраняем в pos
            for (int j=0;j<getP()-1;j++){
                if(array[0][j]==pos1){
                    /*далее проходим по всем Ui и сравниваем его с pos(bi)
                        если Ui = bi то выполняем следующие действия
                    */
                    pos2=array[0][j]-1;//запоминаем Ui в pos2 для дальнейшего обрашения к Ai
                    tmp=array[2][pos2];// сохраняем Apos2
                    break;//выходим из цикла
                }
            }
           array[4][i]=tmp% P  ;//заносим в MH(i) значение Apos2

        }
    }

    public void fill_6throw(){
        int tmp=0;
        for (int i=0;i<getP()-1;i++){
            if(array[4][i]%2==0){
                array[5][i]=-1;
            }else {array[5][i]=1;}
        }
    }

    public void printArray(){
        for (int i=0;i<6;i++){
            for(int j=0;j<getP()-1;j++){
                System.out.printf(array[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
