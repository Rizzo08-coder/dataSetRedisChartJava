import javax.swing.*;

public class SwingChar {
    static final String QUERY_TEMP = "roomTemp.*";
    static final String RADICAL_TEMP = "roomTemp.";
    static final String QUERY_HUM = "roomHum.*";
    static final String RADICAL_HUM = "roomHum.";
    private DataSet dataSetTemperature;
    private DataSet dataSetHumidity;
    public SwingChar(){
        dataSetTemperature = new DataSet(QUERY_TEMP, RADICAL_TEMP);
        dataSetHumidity = new DataSet(QUERY_HUM, RADICAL_HUM);
    }

    public void drawGraph(DataSet dataSet, String title, String nameFrame, String nameLine){
        SwingUtilities.invokeLater(() -> {
            TimeSeriesChart example = new TimeSeriesChart(title, dataSet.getKeyValueMap(), nameFrame,nameLine);
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setVisible(true);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        });
    }

    public DataSet getDataSetTemperature() {
        return dataSetTemperature;
    }

    public DataSet getDataSetHumidity() {
        return dataSetHumidity;
    }
}
