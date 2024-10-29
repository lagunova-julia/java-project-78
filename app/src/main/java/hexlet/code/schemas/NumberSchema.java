package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        Predicate<Integer> isNotEmpty = integer -> integer != null;
        super.predicates.add(isNotEmpty);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> isPositive = integer -> integer == null || integer > 0;
        super.predicates.add(isPositive);
        return this;
    }

    public NumberSchema range(int start, int end) {
        Predicate<Integer> isInRange = integer -> integer >= start && integer <= end;
        super.predicates.add(isInRange);
        return this;
    }
}
