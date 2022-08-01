import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class TimeSeriesChart extends JFrame {
    public TimeSeriesChart(String title, Map<LocalTime, String> map,String nameFrame, String nameLine) {
        super(title);
        // Create dataset
        XYDataset dataset = createDataset(map, nameLine);
        // Create chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                nameFrame, // Chart
                "Time", // X-Axis Label
                "Value", // Y-Axis Label
                dataset);

        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));   //dd/MM/YYYY
        plot.setBackgroundPaint(new Color(255,228,196));
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(Map<LocalTime, String> map, String nameLine) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        LocalDate localDate = LocalDate.now();

        TimeSeries series1 = new TimeSeries(nameLine,"Time","No:of files");
        for(Map.Entry<LocalTime, String> entry : map.entrySet()) {
            LocalTime localTime = entry.getKey();
            String value = entry.getValue();
            series1.addOrUpdate(new Second(localTime.getSecond(), localTime.getMinute(), localTime.getHour(),
                    localDate.getDayOfMonth(), localDate.getMonth().getValue(), localDate.getYear()), Double.parseDouble(value));
        }
        dataset.addSeries(series1);
        return dataset;
    }
}
