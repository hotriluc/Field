import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test3 {
    public static void main(String[]args)throws IOException {
        CDS c = new CDS();
        List<Integer>list = new ArrayList<>();
        //list = c.getSelectedDecimationCoef(18);
        for(int coef:list){
            System.out.println(coef);
        }
        //c.SourceSigWithSigList(FVK.PFVK,false);
    }
}
