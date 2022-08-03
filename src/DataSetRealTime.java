import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class DataSetRealTime implements Runnable{
    public TimeSeries ts = new TimeSeries("line","Time","No:of files");

    private String query;
    private String radical;
    public DataSetRealTime(String query, String radical){
        this.query=query;
        this.radical=radical;
    }

    public synchronized void run() {
        while(true) {
            DataSet dataSet = new DataSet(query, radical);
            for(Map.Entry<LocalTime, String> entry : dataSet.getKeyValueMap().entrySet()) {
                LocalTime localTime = entry.getKey();
                String value = entry.getValue();
                ts.addOrUpdate(new Second(localTime.getSecond(), localTime.getMinute(), localTime.getHour(),
                        LocalDate.now().getDayOfMonth(), LocalDate.now().getMonth().getValue(), LocalDate.now().getYear()), Double.parseDouble(value));
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}