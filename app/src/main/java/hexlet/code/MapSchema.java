package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, String>> {
    private Integer sizeValue = null;
    private Map<String, BaseSchema<String>> shapeSchemas = new HashMap<>();
    public MapSchema sizeof(int size) {
        sizeValue = size;
        Predicate<Map<String, String>> mapSize = stringStringMap -> stringStringMap.size() == sizeValue;
        super.predicates.add(mapSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        this.shapeSchemas.putAll(schemas);
        Predicate<Map<String, String>> shapedMap = map -> {
            for (Map.Entry<String, BaseSchema<String>> entry : shapeSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();
                String value = map.get(key);
                if (!schema.isValid(value)) {
                    return false;
                }
            }
            return true;
        };
        super.predicates.add(shapedMap);
        return this;
    }
}
