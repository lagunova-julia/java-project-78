package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {
    private List<Predicate<String>> stringCheckers = new ArrayList<>();
    private Integer minLengthValue = null;
    public StringSchema required() {
        Predicate<String> isNotEmpty = s -> s != null && !s.trim().isEmpty();
        stringCheckers.add(isNotEmpty);
        return this;
    }

    public StringSchema minLength(int length) {
        minLengthValue = length;
        Predicate<String> miLength = s -> s.length() >= minLengthValue;
        stringCheckers.add(miLength);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> containsSubstr = s -> s.toLowerCase().contains(substring.toLowerCase());
        stringCheckers.add(containsSubstr);
        return this;
    }

    public boolean isValid(String value) {
        for (Predicate<String> checker : stringCheckers) {
            if (!checker.test(value)) {
                return false;
            }
        }
        return true;
    }
}
