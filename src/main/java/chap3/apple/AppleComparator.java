package chap3.apple;

import chap2.apple.Apple;
import java.util.Comparator;

public class AppleComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2 ) {
        return Integer.compare(a1.getWeight(), a2.getWeight());
    }
}
