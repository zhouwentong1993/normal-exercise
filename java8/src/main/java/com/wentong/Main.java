package com.wentong;

import com.wentong.vo.Dish;
import com.wentong.vo.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;
        List<Student> collect3 = menu.stream().map(d -> new Student(d.getName(), d.getCalories())).collect(Collectors.toList());
        System.out.println(collect3);
        List<String> collect = menu.stream().filter(d -> d.getCalories() < 400).sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).limit(3).collect(Collectors.toList());
        System.out.println(collect);

        List<Dish> vegetarianDishes = new ArrayList<>();
        for (Dish d : menu) {
            if (d.isVegetarian()) {
                vegetarianDishes.add(d);
            }
        }
        System.out.println(vegetarianDishes);

        List<Dish> collect1 = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(collect1);

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(n -> n % 2 == 0).distinct().forEach(System.out::println);

        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        int[] ints = words.stream().mapToInt(String::length).toArray();
        System.out.println(ints[3]);

        String s = "hello world";
        String[] split = s.split("");
        List<String> collect2 = Arrays.stream(split).distinct().collect(Collectors.toList());
        System.out.println(collect2);

//        Arrays.stream(s)

    }
}
