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
            System.out.println(dataSet.getKeyValueMap().toString()); //debug line
            for(Map.Entry<LocalTime, String> entry : dataSet.getKeyValueMap().entrySet()) {
                LocalTime localTime = entry.getKey();
                String value=null;
                if(localTime!=null)
                value = entry.getValue();
                double val = Utility.convertStringToDouble(value);
                if (val != -100) {
                    Second istant = new Second(localTime.getSecond(), localTime.getMinute(), localTime.getHour(),
                            LocalDate.now().getDayOfMonth(), LocalDate.now().getMonth().getValue(), LocalDate.now().getYear());
                    ts.addOrUpdate(istant, val);  //vedere ClassCastException e ArrayIndexOutofbound
                }

            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}
