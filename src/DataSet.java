import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DataSet {
    private final RedisClient redisClient;
    private final Set<String> keysTemperature;
    private final List<String> valuesTemperature;
    private final Map<LocalTime, String> keyValueMap;

    public DataSet(){
        redisClient = new RedisClient("192.168.178.25", 6379, 100, "admin");
        keysTemperature = redisClient.getKeysByString("roomTemp.*");
        valuesTemperature = redisClient.getValuesByKeys(keysTemperature);
        keyValueMap = associationKeyValue(keysTemperature, valuesTemperature);
    }

    private Map<LocalTime, String> associationKeyValue(Set<String> keys, List<String> values) {
        Map<LocalTime, String> keyValue = new TreeMap<>();
        String[] arrayKeys = Utility.convertSetToArray(keys);
        for (int i = 0; i < arrayKeys.length; i++) {
            arrayKeys[i] = Utility.removeRadicalFromKey(arrayKeys[i], "roomTemp.");
        }
        LocalTime[] arrayKeysTime = new LocalTime[keys.size()];
        for (int i = 0; i < arrayKeysTime.length; i++) {
            arrayKeysTime[i] = Utility.convertStringToLocalTime(arrayKeys[i]);
        }
        String[] arrayValues = Utility.convertListToArray(values);
        for (int i = 0; i < keys.size(); i++) {
            keyValue.put(arrayKeysTime[i], arrayValues[i]);
        }
        return keyValue;
    }

    public Map<LocalTime, String> getKeyValueMap() {
        return keyValueMap;
    }
}
