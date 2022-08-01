import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingChar swingChar = new SwingChar();
        swingChar.drawGraph(swingChar.getDataSetTemperature(), "Temperature Chart", "Temperature", "temperature");
        swingChar.drawGraph(swingChar.getDataSetHumidity(), "Humidity Chart", "Humidity", "humidity");
    }
}
