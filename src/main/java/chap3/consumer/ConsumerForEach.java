package chap3.consumer;

import java.util.List;

public class ConsumerForEach {
    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }
}
