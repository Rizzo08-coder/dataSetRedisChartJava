import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class DrawGraph{
    public DrawGraph(String title, String nameX, String nameY, String query, String radical, String nameImage) {
        DataSetRealTime dataRealTime = new DataSetRealTime(query, radical);
        new Thread(dataRealTime).start();

        TimeSeriesCollection dataset = new TimeSeriesCollection(dataRealTime.getTimeSeries());
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                nameX,
                nameY,
                dataset,
                true,
                true,
                false
        );
        chart.getAntiAlias();
        final XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(4.0f));
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        axis.setAutoRange(true);
        //axis.setFixedAutoRange(700000.0);

        //settare una dimensione adatta, troppi punti che si vedono generano eccezione ?!

        JFrame frame = new JFrame("Grafico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton buttonSaveImage = new JButton("save(.png)");
        buttonSaveImage.addActionListener(new SaveButton(nameImage, chart));
        ChartPanel label = new ChartPanel(chart);
        frame.add(label, BorderLayout.CENTER);
        frame.getContentPane().add(buttonSaveImage, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.repaint();
    }
}
