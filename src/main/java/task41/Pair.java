package task41;

import java.util.Objects;

public class Pair<T1, T2> {
    private final T1 key;
    private final T2 value;

    public Pair(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    public T1 getKey() {
        return key;
    }

    public T2 getValue() {
        return value;
    }

    public T1 getLeft() {
        return key;
    }

    public T2 getRight() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + key +
                ", second=" + value +
                '}';
    }
}
