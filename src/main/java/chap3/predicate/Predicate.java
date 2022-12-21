package chap3.predicate;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
