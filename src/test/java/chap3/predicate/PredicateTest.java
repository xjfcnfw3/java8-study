package chap3.predicate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PredicateTest {
    @Test
    void predicateTest() {
        List<String> strings = Arrays.asList(
            "", "abc", "", "1233", ""
        );
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = Filter.filter(strings, nonEmptyStringPredicate);
        System.out.println("nonEmpty = " + nonEmpty);
    }
}
