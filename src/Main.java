import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DataSet dataSetRedis = new DataSet();
        System.out.println(dataSetRedis.getKeyValueMap().toString());  //debug line

        SwingUtilities.invokeLater(() -> {
            TimeSeriesChart example = new TimeSeriesChart("Temperature Chart", dataSetRedis.getKeyValueMap());
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setVisible(true);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        });
    }
}
