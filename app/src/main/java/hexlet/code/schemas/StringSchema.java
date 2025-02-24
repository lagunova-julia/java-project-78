package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    private Integer minLengthValue = null;
    public StringSchema required() {
        Predicate<String> isNotEmpty = s -> s != null && !s.trim().isEmpty();
        addCheck("required", isNotEmpty);
        return this;
    }

    public StringSchema minLength(int length) {
        minLengthValue = length;
        Predicate<String> minLength = s -> s.length() >= minLengthValue;
        addCheck("minLength", minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> containsSubstr = s -> s.toLowerCase().contains(substring.toLowerCase());
        addCheck("contains", containsSubstr);
        return this;
    }
}
