import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Set;

public class RedisClient {
    private static JedisPool jedisPool;

    public RedisClient(String ip, int port, int timeout, String password) {
        try {
            if (jedisPool == null) {
                JedisPoolConfig poolConfig = new JedisPoolConfig();
                jedisPool = new JedisPool(poolConfig, ip, port, timeout, password);
            }
        } catch (Exception e) {
            System.out.println("Malformed server address");
        }
    }

    public Set<String> getKeysByString(String interrogation){
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(interrogation);
        } catch (Exception ex) {
            System.out.println("Exception caught in KEYS");
        }
        return null;
    }

    public List<String> getValuesByKeys(Set<String> keys){
        String[] arrayKeys = Utility.convertSetToArray(keys);
        try (Jedis jedis = jedisPool.getResource()) {
            List<String> values = jedis.mget(arrayKeys);
            return values;
        } catch (Exception ex) {
            System.out.println("Exception caught in mget");
        }
        return null;
    }
}
