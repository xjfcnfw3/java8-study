package chap2.predicate;

import chap2.apple.Apple;

public class AppleGreenColorPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
