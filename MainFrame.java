import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame  {
    private JPanel panel;

    MainFrame(java.util.List<Integer> pfak_list, java.util.List<Integer> afak_list, java.util.List<Integer> pfvk_list, java.util.List<Integer> afvk_list){

        Plot chart1 = new Plot("ПФАК",
                "ПФАК",pfak_list);
        Plot chart2 = new Plot("АФАК",
                "АФАК",afak_list);
        Plot chart3 = new Plot("ПФВК",
                "ПВФК",pfvk_list);
        Plot chart4 = new Plot("АФВК",
                "АВФК",afvk_list);

        JFrame frame = new JFrame();

        frame.setLayout(new FlowLayout(FlowLayout.CENTER,40,40));
        frame.getContentPane().add(chart1.getChartPanel());

        frame.getContentPane().add(chart2.getChartPanel());

        frame.getContentPane().add(chart3.getChartPanel());

        frame.getContentPane().add(chart4.getChartPanel());
        frame.setSize(600, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);


    }



    public static void main(String[]args){
        ArrayList<Integer> al = new ArrayList<>();
        al.add(2);
        al.add(3);
        al.add(4);
        Plot chart = new Plot("ПФАК",
                "ПФАК",al);

        Plot chart1 = new Plot("ПФАК2",
                "ПФАК2",al);
        Plot chart2 = new Plot("ПФАК2",
                "ПФАК2",al);
        Plot chart3 = new Plot("ПФАК2",
                "ПФАК2",al);
        Plot chart4 = new Plot("ПФАК2",
                "ПФАК2",al);
        JFrame frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(chart.getChartPanel());

        frame.getContentPane().add(chart1.getChartPanel());

        frame.getContentPane().add(chart2.getChartPanel());

        frame.getContentPane().add(chart3.getChartPanel());





        //frame.getContentPane().add(chart1.getChartPanel());


        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);




    }


}
