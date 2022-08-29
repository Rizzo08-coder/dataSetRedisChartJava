import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SaveButton implements ActionListener {
    private String nameImage;
    private JFreeChart chart;
    public SaveButton(String nameImage, JFreeChart chart){
        this.nameImage=nameImage;
        this.chart=chart;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            System.out.println("saving image...");
            ChartUtils.saveChartAsPNG(new File(nameImage), chart, 1600, 800);
            System.out.println("image saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
