public class Main {
    public static void main(String[] args) {
        DrawGraph chartTemp = new DrawGraph("TEMPERATURE", "time", "valueTemperature", "roomTemp.*", "roomTemp.");
        DrawGraph chartHum = new DrawGraph("HUMIDITY", "time", "valueHumidity", "roomHum.*", "roomHum.");
    }
}
