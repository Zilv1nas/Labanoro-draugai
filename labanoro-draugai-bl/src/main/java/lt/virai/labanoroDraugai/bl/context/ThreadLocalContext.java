package lt.virai.labanoroDraugai.bl.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Žilvinas on 2016-05-21.
 */
public class ThreadLocalContext {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(HashMap::new);

    public static void put(String key, Object value) {
        THREAD_LOCAL.get().put(key, value);
    }

    public static Object get(String key) {
        return THREAD_LOCAL.get().get(key);
    }
}
