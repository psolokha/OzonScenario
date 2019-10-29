package ru.aplana.study.ozon.utils;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class ItemsContainer {

    private  TreeMap<String, Integer> container;
    private static ItemsContainer instance;

    private ItemsContainer() {
        container = new TreeMap<>();
        instance = this;
    }

    public static ItemsContainer getInstance() {
        return instance == null ? new ItemsContainer() : instance;
    }

    public void putItem(String item, Integer price) {
        if (container.containsKey(item)) container.replace(item, (container.get(item) + price));
        else container.put(item, price);
    }

    public void removeItem(String item, Integer price) {
        if (container.get(item).equals(price)) container.remove(item);
        else container.replace(item, (container.get(item) - price));
    }

    public Integer getNumberItems() {
        return container.size();
    }

    public void removeAll() {
        container.clear();
    }

    public String checkMostExpensive() {
            Map.Entry<String, Integer> maxPrice = container.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get();
        return maxPrice.getKey() + ": " + maxPrice.getValue();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        container.forEach((item, price) -> stringBuilder.append(item).append(": ").append(price).append('\n'));
        return stringBuilder.toString();
    }

}
