package hexlet.code;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {
    private Integer minLengthValue = null;
    public StringSchema required() {
        Predicate<String> isNotEmpty = s -> s != null && !s.trim().isEmpty();
        super.predicates.add(isNotEmpty);
        return this;
    }

    public StringSchema minLength(int length) {
        minLengthValue = length;
        Predicate<String> miLength = s -> s.length() >= minLengthValue;
        super.predicates.add(miLength);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> containsSubstr = s -> s.toLowerCase().contains(substring.toLowerCase());
        super.predicates.add(containsSubstr);
        return this;
    }
}
