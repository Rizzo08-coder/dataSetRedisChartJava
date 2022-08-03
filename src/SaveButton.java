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
            ChartUtils.saveChartAsPNG(new File(nameImage), chart, 800, 500);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
