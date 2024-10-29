package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected List<Predicate<T>> predicates = new ArrayList<>();

    public BaseSchema<T> required() {
        Predicate<T> isNotEmpty = value -> value != null;
        predicates.add(isNotEmpty);
        return this;
    }

    public boolean isValid(T value) {
        for (Predicate<T> predicate : predicates) {
            if (!predicate.test(value)) {
                return false;
            }
        }
        return true;
    }
}
