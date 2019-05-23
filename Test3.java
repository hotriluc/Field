import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {
    public static void main(String[]args)throws IOException {
        CDS c = new CDS();
        List<int[]>list = new ArrayList<>();
        //list = c.getSelectedDecimationCoef(18);

         list = c.getDecimation_list(c.getField().getPsi(),c.getField().getEuler().getCoprime());
        for(int i =0;i<list.size();i++){
            System.out.println("ХДС Коеф.дец "+c.getField().getEuler().getCoprime().get(i)+"\n"+Arrays.toString(list.get(i)));
        }


        c.SourceSigWithSigList(FVK.PFVK,true);
        //c.SourceSigWithSigList(FVK.PFVK,false);
    }
}
