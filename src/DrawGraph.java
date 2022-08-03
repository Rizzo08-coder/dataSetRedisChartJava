import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class DrawGraph {
    public DrawGraph(String title, String nameX, String nameY, String query, String radical){
        Runnable dataRealTime = new DataSetRealTime(query, radical);
        new Thread(dataRealTime).start();

        TimeSeriesCollection dataset = new TimeSeriesCollection(((DataSetRealTime) dataRealTime).ts);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                nameX,
                nameY,
                dataset,
                true,
                true,
                false
        );
        final XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0f));
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        axis.setAutoRange(true);
       // axis.setFixedAutoRange(60000.0);

        //settare una dimensione adatta, troppi punti che si vedono generano eccezione ?!

        JFrame frame = new JFrame("GraphTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel label = new ChartPanel(chart);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);

    }
}
