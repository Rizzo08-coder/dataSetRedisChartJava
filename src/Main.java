import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        DrawGraph chartTemp = new DrawGraph("TEMPERATURE","time", "valueTemperature", "roomTemp.*", "roomTemp." ,"imageTemp");
        DrawGraph chartHum = new DrawGraph("HUMIDITY", "time", "valueHumidity", "roomHum.*", "roomHum.", "imageHum");

    }
}
