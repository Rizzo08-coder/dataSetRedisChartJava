public class Main {
    public static void main(String[] args) {
        SwingChart swingChar = new SwingChart();
        swingChar.drawGraph(swingChar.getDataSetTemperature(), "Temperature Chart", "Temperature", "temperature");
        swingChar.drawGraph(swingChar.getDataSetHumidity(), "Humidity Chart", "Humidity", "humidity");
    }
}
