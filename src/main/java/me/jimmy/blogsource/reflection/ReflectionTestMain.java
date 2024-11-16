package me.jimmy.blogsource.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionTestMain {
    public static void main(String[] args) throws Exception  {
        int tryCount = 10_000_000;
        long start = System.currentTimeMillis();
        usingReflection(tryCount);
//        usingNormal(tryCount);
        long end = System.currentTimeMillis();
        System.out.println("소요시간 : " + (end - start) + "ms");
        Thread.sleep(3000);
    }

    private static void usingNormal(int tryCount) {
        System.out.println("normal start");
        List<Object> list = new ArrayList<>(tryCount);
        for (int i = 0; i < tryCount; i++) {
            PerformanceUser performanceUser = new PerformanceUser();
            performanceUser.setName("name" + i);
            performanceUser.setAge(i);
            list.add(performanceUser);
        }
        System.out.println("normalEnd, listSize = " + list.size());
    }

    private static void usingReflection(int tryCount) throws Exception {
        System.out.println("reflect start");
        List<Object> list = new ArrayList<>(tryCount);
        for (int i = 0; i < tryCount; i++) {
            Class<?> aClass = Class.forName("me.jimmy.blogsource.reflection.PerformanceUser");
            Constructor<?> constructor = aClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();

            Field idField = aClass.getDeclaredField("name");
            idField.setAccessible(true);
            idField.set(instance, "name" + i);

            Field ageField = aClass.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.set(instance, i);
            list.add(instance);
        }

        System.out.println("reflect end, listSize = " + list.size());
    }
}
