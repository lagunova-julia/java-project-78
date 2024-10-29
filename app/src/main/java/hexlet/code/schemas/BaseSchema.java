package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс BaseSchema предоставляет базовую функциональность для валидации значений
 * с использованием предикатов.
 *
 * @param <T> Тип значений, которые будут проверяться.
 */
public class BaseSchema<T> {
    protected List<Predicate<T>> predicates = new ArrayList<>();

    /**
     * Добавляет предикат, проверяющий, что значение не равно null,
     * и возвращает текущий экземпляр схемы.
     */
    public BaseSchema<T> required() {
        Predicate<T> isNotEmpty = value -> value != null;
        predicates.add(isNotEmpty);
        return this;
    }

    /**
     * Проверяет, является ли переданное значение валидным,
     * основываясь на добавленных предикатах.
     */
    public final boolean isValid(T value) {
        for (Predicate<T> predicate : predicates) {
            if (!predicate.test(value)) {
                return false;
            }
        }
        return true;
    }
}
