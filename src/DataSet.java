import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DataSet {
    private static final String IP_ADDRESS = "192.168.178.25";
    private static final int TCP_PORT = 6379;
    private static final String PSW_DB= "admin";
    private final RedisClient redisClient;
    private final Set<String> keys;
    private final List<String> values;
    private final Map<LocalTime, String> keyValueMap;

    public DataSet(String query, String radical){
        redisClient = new RedisClient(IP_ADDRESS, TCP_PORT, 100, PSW_DB);
        keys = redisClient.getKeysByString(query);
        values = redisClient.getValuesByKeys(keys);
        keyValueMap = associationKeyValue(keys, values, radical);
    }

    private Map<LocalTime, String> associationKeyValue(Set<String> keys, List<String> values, String radical) {
        Map<LocalTime, String> keyValue = new TreeMap<>();
        String[] arrayKeys = Utility.convertSetToArray(keys);
        LocalTime[] arrayKeysTime = new LocalTime[keys.size()];
        for (int i = 0; i < arrayKeys.length; i++) {
            arrayKeys[i] = Utility.removeRadicalFromKey(arrayKeys[i], radical);
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
