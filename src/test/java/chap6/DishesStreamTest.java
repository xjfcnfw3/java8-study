package chap6;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static org.assertj.core.api.Assertions.assertThat;

import chap4.Dish;
import chap4.Dish.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DishesStreamTest {

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void getMaxCaloriesDish(List<Dish> menu) {
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        System.out.println("mostCalorieDish = " + mostCalorieDish);
        assertThat(mostCalorieDish.get().getName()).isEqualTo("pork");
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void getSumCaloriesDishes(List<Dish> menu) {
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("totalCalories = " + totalCalories);
        assertThat(totalCalories).isEqualTo(4200);
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void getSummaryDishes(List<Dish> menu) {
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("menuStatistics = " + menuStatistics);
        assertThat(menuStatistics.getCount()).isEqualTo(9);
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void getJoinedDishNames(List<Dish> menu) {
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println("shortMenu = " + shortMenu);
        assertThat(shortMenu).contains("pork");
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void reducingMostCalorie(List<Dish> menu) {
        Optional<Dish> mostCalorieDish = menu.stream()
            .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println("mostCalorieDish = " + mostCalorieDish);
    }


    @MethodSource("getTestDishes")
    @ParameterizedTest
    void groupByMultipleLevels(List<Dish> menu) {
        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(groupingBy(Dish::getType,
            groupingBy(dish -> {
                if (dish.getCalories() <= 400) {
                    return CaloricLevel.DIET;
                } else if (dish.getCalories() <= 700) {
                    return CaloricLevel.NORMAL;
                } else {
                    return CaloricLevel.FAT;
                }
            })));
        System.out.println("dishesByTypeCaloricLevel = " + dishesByTypeCaloricLevel);
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void groupByMostCaloriesDish(List<Dish> menu) {
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
            .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        System.out.println("mostCaloricByType = " + mostCaloricByType);
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void groupByMostCaloriesDishNotOptional(List<Dish> menu) {
        Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
            .collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                Optional::get)));
        System.out.println("mostCaloricByType = " + mostCaloricByType);
    }

    @MethodSource("getTestDishes")
    @ParameterizedTest
    void partitioningByVegetarian(List<Dish> menu) {
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("partitionedMenu = " + partitionedMenu);
    }

    public static Stream<List<Dish>> getTestDishes() {
        return Stream.of(Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)));
    }
}
