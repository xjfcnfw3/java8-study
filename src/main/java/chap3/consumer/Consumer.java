package chap3.consumer;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
