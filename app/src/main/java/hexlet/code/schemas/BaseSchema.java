package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Класс BaseSchema предоставляет базовую функциональность для валидации значений
 * с использованием предикатов.
 *
 * @param <T> Тип значений, которые будут проверяться.
 */
public class BaseSchema<T> {
    protected Map<String, Predicate<T>> predicates = new LinkedHashMap<>();

    /**
     * Добавляет проверку с указанным именем и связанным предикатом.
     *
     * Этот метод позволяет зарегистрировать новую проверку,
     * которая может быть позже оценена на основе предоставленного
     * предиката. Предикат должен содержать логику для определения
     * результата проверки. Проверки можно будет ссылаться по их
     * имени для последующей оценки.
     *
     * @param checkName имя проверки; должно быть уникальным и
     *                  не может быть null
     * @param predicate предикат, который будет ассоциирован с
     *                  проверкой; не может быть null; этот
     *                  предикат должен быть типа Predicate<T>
     *
     * @throws IllegalArgumentException если checkName равно null или
     *         если predicate равно null
     */
    public void addCheck(String checkName, Predicate<T> predicate) {
        predicates.put(checkName, predicate);
    }

    /**
     * Добавляет предикат, проверяющий, что значение не равно null,
     * и возвращает текущий экземпляр схемы.
     *
     * @return текущий экземпляр {@link BaseSchema}
     */
    public BaseSchema<T> required() {
        Predicate<T> isNotEmpty = value -> value != null;
        addCheck("required", isNotEmpty);
        return this;
    }

    /**
     * Проверяет, является ли переданное значение валидным,
     * основываясь на добавленных предикатах.
     *
     * @param value значение, которое нужно проверить
     * @return true, если значение проходит все предикаты, иначе false
     */
    public final boolean isValid(T value) {
        for (Predicate<T> predicate : predicates.values()) {
            if (!predicate.test(value)) {
                return false;
            }
        }
        return true;
    }
}
