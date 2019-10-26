package ru.aplana.study.ozon.utils;

import java.util.HashMap;

public class ItemsContainer {

    private static HashMap<String, Integer> container = new HashMap<>();

    public static void putItem(String item, Integer price) {
        if (container.containsKey(item)) container.replace(item, (container.get(item) + price));
        else container.put(item, price);
    }

    public static void removeItem(String item, Integer price) {
        if (container.get(item) == price) container.remove(item);
        else container.replace(item, (container.get(item) - price));
    }

    public static Integer getNumberItems() {
        return container.size();
    }

    public static void printItems() {
        System.out.println(new ItemsContainer().toString());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        container.forEach((item, price) -> stringBuilder.append(item + ": " + price + '\n'));
        return stringBuilder.toString();
    }
}
