import org.jfree.ui.RefineryUtilities;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class asciiToBin {
    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static String getAsciTOBin(String s){
        //Converting ASCII TO BIN without leading zeros
        char[] carr = s.toCharArray();
        StringBuilder bin = new StringBuilder();
        for (int i =0;i<carr.length;i++){
            int ascii = (int)carr[i];
            bin.append(String.format(Integer.toBinaryString(ascii)));
        }
       return new String(bin.toString());
    }

    public static String fromByteToString(int[] arr,int from, int to){
        assert (to>arr.length);
        StringBuilder sb = new StringBuilder();
        for(int i = from;i<to;i++){
            sb.append(Integer.toBinaryString(arr[i])+" ");
        }
        return  new String(sb.toString());
    }

    public static void main(String[] args) {
        String s = "6 ";
       // byte[] nb = s.getBytes();
       // System.out.println(Arrays.toString(nb));
       String bin_string = asciiToBin.getAsciTOBin(s);


        char[]tmp = bin_string.toCharArray();

        //Comparing first index val with 1
        System.out.println(Integer.parseInt(String.valueOf(tmp[0]))==1 );

        //Writing char 1 by 1 into int array
        int[]dig_tmp = new int[tmp.length];
        for(int i=0;i<tmp.length;i++){
            dig_tmp[i]= Integer.parseInt(String.valueOf(tmp[i]));
        }
        System.out.println(Arrays.toString(dig_tmp)+"\n");

        /*byte [] newb = null;
        try {
            FileInputStream fin = new FileInputStream("Kalyna.dat");
            newb = new byte[fin.available()];
            fin.read(newb,0,fin.available());
            System.out.println(newb.length);
        }catch (IOException e){}


        for (int i =0;i<10;i++){
            System.out.println(newb[i]+" = "+Integer.toBinaryString(newb[i]));
        }

        System.out.println(fromByteToString(newb,0,5));*/

        int [] b = new int[1024000];
        int cnt = 0;
        try (RandomAccessFile raf = new RandomAccessFile("Kalyna.dat", "rw")) {
            //Stop while reaching EOF = -1
           while (raf.read()!=-1){

               b[cnt]=raf.read();
               cnt++;
           }
         //Считываем с файла от 0 до 20 по байтно
            //поставим потом seek чтобы можно было считывать от конкретной позиции файла
            System.out.println("Reading from file");
           // raf.seek(8191);
         /*for (int i = 0 ;i<20;i++){
             b[i]= raf.read();
         }*/
        }catch (IOException e){}

        //Выводим послдовательность байт
        //System.out.println(Arrays.toString(b));

        //Переводим последовательность байт в бинарный вид
        String sequence  = fromByteToString(b,0,b.length);
       // System.out.println(sequence);
        //sequence.replaceAll("\\s","");

        //Converting sequence to char array without whitespace;
        char[] arr_wo = sequence.replaceAll("\\s","").toCharArray();
        System.out.println("Seq lenght w/o whitespace = "+arr_wo.length);


        int[]dig_tmp1 = new int[arr_wo.length];
        for(int i=0;i<dig_tmp1.length;i++){
            dig_tmp1[i]= Integer.parseInt(String.valueOf(arr_wo[i]));
        }
        //System.out.println(Arrays.toString(dig_tmp1)+"\n");

        //ПРОИНВЕРТИРОВАТЬ 0 = -1


        for(int i=0;i<dig_tmp1.length;i++){
            if(dig_tmp1[i]==0) {
                dig_tmp1[i] = -1;
            }
        }
        int start=0;
        int finish=130;
        int p = finish;
        int[] somearr;
        while (true) {
             somearr = Arrays.copyOfRange(dig_tmp1, start, finish);
            if(finish>dig_tmp1.length){System.out.println("Finish");break;}
            int[] shift_arr = somearr.clone();
            Signal sig = new Signal(somearr.length);
            sig.setSignal(shift_arr);
            System.out.println("PFAK");
            List<Integer> pereodic_auto_correl_list = sig.getAutoCorrelList(somearr);
            int rmax_aper = StatClass.getRmaxWO(pereodic_auto_correl_list, somearr.length);
            System.out.println("\nRmax=" + rmax_aper);
            System.out.println("Count: " + StatClass.getCntAndPos(pereodic_auto_correl_list, rmax_aper));
            if(rmax_aper<=10){
                break;
            }else{
                finish+=3;
                start+=3;
            }
        }
    /*AFAR
        Signal s2 = new Signal(p);
        int[] arr2 = somearr.clone(); // will be shifted
        s2.setSignal(arr2);
        System.out.println("AFAK");

        List<Integer>apereodic_auto_correl_list = s2.getApereodicCorrelList(somearr);
        apereodic_auto_correl_list.addAll(apereodic_auto_correl_list);
        int rmax_aper =StatClass.getRmaxWO(apereodic_auto_correl_list, p );
        System.out.println("\nRmax="+rmax_aper);
        System.out.println("Count: "+StatClass.getCntAndPos(apereodic_auto_correl_list,rmax_aper));

        */


    }
}
