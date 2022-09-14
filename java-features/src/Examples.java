import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toSet;

/**
 * Multiple classes in file
 */
class ExtraClass {

}

public class Examples {


    public static void labels() {
        outer:
        for (int y = 0; y < 3; ++y) {
            inner:
            for (int x = 0; x < 3; ++x) {
                System.out.printf("%d x %d \n", x, y);
                if (x == 2)
                    break outer;
            }
        }
    }


    public static void link() {
        http://seznam.cz
        System.out.println("link");
    }

    public static void computeIfAbsent() {
        Map<Integer, List<String>> grouped = new HashMap<>();

        String[] strings = {"ab", "ccd", "ffffd"};

        for (String string : strings) {
            int key = string.length();
            addToMapOld(grouped, string, key);
            addToMapNew(grouped, string, key);
        }
    }

    private static void addToMapOld(Map<Integer, List<String>> grouped, String string, int key) {
        List<String> list = grouped.get(key);
        if (list == null) {
            list = new ArrayList<>();
            grouped.put(key, list);
        }
        list.add(string);
    }

    private static void addToMapNew(Map<Integer, List<String>> grouped, String string, int key) {
        // lambda argument
        List<String> targetList = grouped.computeIfAbsent(key, k -> new ArrayList<>());
        targetList.add(string);
    }

    private static int producer() {
        return 4;
    }

    private static void consumer(int input) {
        System.out.println(input);
    }

    public static void exampleOfWar() {

        Map<Integer, String> exampleMap = Map.of(
                1, "a",
                2, "b"
        );

        // extra lookup
        for (Integer key : exampleMap.keySet()) {
            String value = exampleMap.get(key);
            System.out.printf("%d %s\n", key, value);
        }

        // complicated datatype
        for (Map.Entry<Integer, String> kv : exampleMap.entrySet()) {
            System.out.printf("%d %s\n", kv.getKey(), kv.getValue());
        }

        // var
        for (var kv : exampleMap.entrySet()) {
            System.out.printf("%d %s\n", kv.getKey(), kv.getValue());
        }

        int value = producer();
        consumer(value);

        // change of return type does not require change of this method
        var value2 = producer();
        consumer(value2);


        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }

            public void extraFunction() {

            }
        };

        // consumer.extraFunction(); // does not work

        var consumer2 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }

            public void extraFunction() {

            }
        };

        consumer2.extraFunction(); // does work
    }

    public static void streams() {
        String[] strings = {"", "1", "2", "3"};

        Set<Integer> processed2 = new HashSet<>();
        for (String x : strings) {
            if (!x.isEmpty()) {
                Integer integer = processData(x);
                processed2.add(integer);
            }
        }

        Set<Integer> processed = Arrays.stream(strings)
                .filter(x -> !x.isEmpty())
                .map(Examples::processData)
                .collect(toSet());


    }

    public static void functions() {
        List<String> data = List.of("1", "2", "3");
        List<String> whitelist = List.of("1");
        filterData1(data, new HashSet<>(whitelist));
        filterData2(data, new HashSet<>(whitelist));
        filterData3(data, new HashSet<>(whitelist));
        filterData4(data, whitelist::contains);
        filterData5inplace(data, whitelist::contains);
    }

    private static List<String> filterData1(List<String> data, Set<String> whitelist) {
        List<String> result = new ArrayList<>();
        for (String dat : data) {
            if (whitelist.contains(dat))
                result.add(dat);
        }
        return result;
    }

    private static List<String> filterData2(List<String> data, Set<String> whitelist) {
        return data.stream()
                .filter(item -> whitelist.contains(item))
                .toList();
    }

    private static List<String> filterData3(List<String> data, Set<String> whitelist) {
        return data.stream()
                .filter(whitelist::contains)
                .toList();
    }

    private static List<String> filterData4(List<String> data, Predicate<String> allow) {
        return data.stream()
                .filter(allow)
                .toList();
    }

    private static void filterData5inplace(List<String> data, Predicate<String> allow) {
        data.removeIf(allow);
    }

    private static Integer processData(String str) {
        return Integer.valueOf(str);
    }
}
