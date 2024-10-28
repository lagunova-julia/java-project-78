package hexlet.code;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, String>> {
    private Integer sizeValue = null;
    public MapSchema sizeof(int size) {
        sizeValue = size;
        Predicate<Map<String, String>> mapSize = stringStringMap -> stringStringMap.size() == sizeValue;
        super.predicates.add(mapSize);
        return this;
    }

}
