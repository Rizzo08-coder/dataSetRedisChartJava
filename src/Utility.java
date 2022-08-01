import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class Utility {
    public static String[] convertSetToArray(Set<String> set) {
        String[] array = new String[set.size()];
        array = set.toArray(array);
        return array;
    }

    public static String[] convertListToArray(List<String> list) {
        String[] array = new String[list.size()];
        array = list.toArray(array);
        return array;
    }

    public static LocalTime convertStringToLocalTime(String localTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localHour = LocalTime.parse(localTime, formatter);
        return localHour;
    }

    public static String removeRadicalFromKey (String key, String radical){
        return key.replace(radical, "");
    }
}
