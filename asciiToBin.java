import java.util.Arrays;

public class asciiToBin {

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

    public static void main(String[] args) {
        String s = "6";

       String bin_string = asciiToBin.getAsciTOBin(s);


        char[]tmp = bin_string.toString().toCharArray();

        //Comparing first index val with 1
        System.out.println(Integer.parseInt(String.valueOf(tmp[0]))==1 );

        //Writing char 1 by 1 into int array
        int[]dig_tmp = new int[tmp.length];
        for(int i=0;i<tmp.length;i++){
            dig_tmp[i]= Integer.parseInt(String.valueOf(tmp[i]));
        }
        System.out.println(Arrays.toString(dig_tmp));
    }
}
